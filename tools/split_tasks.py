#!/usr/bin/env python3
"""
Сплиттер задач курса: TaskNN.java с несколькими top-level типами → папка TaskNN/
с отдельным файлом на каждый тип. Default package сохраняется. Импорты дублируются
в каждый файл (лишние — лишь предупреждения). JavaDoc задачи переносится в "главный"
файл (тип TaskNN, либо *Test, либо первый тип).

Использование:
  python split_tasks.py <file.java | dir> [--apply]
Без --apply — dry-run (только показывает план).
"""
import sys, os, re
try:
    sys.stdout.reconfigure(encoding='utf-8')
except Exception:
    pass

DECL = re.compile(
    r'^\s*(?:(?:public|protected|private|final|abstract|sealed|non-sealed|strictfp|static)\s+)*'
    r'(@interface|class|interface|enum|record)\s+([A-Za-z_]\w*)'
)


def mask(s):
    """Заменяет содержимое строк/символов/комментариев/text-блоков на пробелы (переводы строк сохраняются)."""
    out = list(s)
    i, n = 0, len(s)
    st = None  # None | line | block | str | char | text
    while i < n:
        c = s[i]
        if st is None:
            if c == '/' and i + 1 < n and s[i + 1] == '/':
                out[i] = out[i + 1] = ' '; st = 'line'; i += 2; continue
            if c == '/' and i + 1 < n and s[i + 1] == '*':
                out[i] = out[i + 1] = ' '; st = 'block'; i += 2; continue
            if c == '"' and s[i:i + 3] == '"""':
                out[i] = out[i + 1] = out[i + 2] = ' '; st = 'text'; i += 3; continue
            if c == '"':
                out[i] = ' '; st = 'str'; i += 1; continue
            if c == "'":
                out[i] = ' '; st = 'char'; i += 1; continue
            i += 1; continue
        if st == 'line':
            if c == '\n': st = None
            else: out[i] = ' '
            i += 1; continue
        if st == 'block':
            if c == '*' and i + 1 < n and s[i + 1] == '/':
                out[i] = out[i + 1] = ' '; st = None; i += 2; continue
            if c != '\n': out[i] = ' '
            i += 1; continue
        if st == 'str':
            if c == '\\' and i + 1 < n:
                out[i] = out[i + 1] = ' '; i += 2; continue
            if c == '"':
                out[i] = ' '; st = None; i += 1; continue
            if c == '\n':
                st = None; i += 1; continue
            out[i] = ' '; i += 1; continue
        if st == 'char':
            if c == '\\' and i + 1 < n:
                out[i] = out[i + 1] = ' '; i += 2; continue
            if c == "'":
                out[i] = ' '; st = None; i += 1; continue
            if c == '\n':
                st = None; i += 1; continue
            out[i] = ' '; i += 1; continue
        if st == 'text':
            if s[i:i + 3] == '"""':
                out[i] = out[i + 1] = out[i + 2] = ' '; st = None; i += 3; continue
            if c != '\n': out[i] = ' '
            i += 1; continue
    return ''.join(out)


def parse(text):
    """Возвращает (header_lines, import_lines, types). types: [{name, lines, prefix}].
    prefix — интерстициальные строки (аннотации/комментарии), стоящие НЕПОСРЕДСТВЕННО
    перед этим типом; они едут вместе со своим типом, а не с первым."""
    lines = text.split('\n')
    mlines = mask(text).split('\n')
    header, imports = [], []
    types = []
    first_seen = False
    depth = 0
    i, n = 0, len(lines)
    cur = None
    brace_opened = False
    pending = []  # интерстициальные строки, ждущие следующий тип
    while i < n:
        line, m = lines[i], mlines[i]
        if cur is None:  # вне типа
            if depth == 0 and DECL.match(m):
                name = DECL.match(m).group(2)
                cur = {'name': name, 'lines': [line], 'prefix': pending}
                pending = []
                first_seen = True
                depth += m.count('{') - m.count('}')
                brace_opened = '{' in m
                if brace_opened and depth == 0:
                    types.append(cur); cur = None
                i += 1; continue
            # интерстициальная строка на depth 0
            if not first_seen:
                if line.lstrip().startswith('import '):
                    imports.append(line)
                else:
                    header.append(line)
            else:
                # префикс к СЛЕДУЮЩЕМУ типу (аннотации/комментарии)
                pending.append(line)
            i += 1; continue
        else:  # внутри типа
            cur['lines'].append(line)
            depth += m.count('{') - m.count('}')
            if '{' in m:
                brace_opened = True
            if brace_opened and depth == 0:
                types.append(cur); cur = None; brace_opened = False
            i += 1; continue
    if cur is not None:  # незакрытый (на всякий случай)
        types.append(cur)
    return header, imports, types


def split_header(header):
    """Из строк до первого импорта выделяет ведущий блок-комментарий (JavaDoc)."""
    # обрезаем ведущие пустые
    k = 0
    while k < len(header) and header[k].strip() == '':
        k += 1
    doc = []
    if k < len(header) and header[k].lstrip().startswith('/*'):
        while k < len(header):
            doc.append(header[k])
            if '*/' in header[k]:
                k += 1; break
            k += 1
    return doc


def choose_primary(types, base):
    for idx, t in enumerate(types):
        if t['name'] == base:
            return idx
    for idx, t in enumerate(types):
        if t['name'].endswith('Test') or 'Test' in t['name']:
            return idx
    return 0


def trim_blanks(lines):
    a, b = 0, len(lines)
    while a < b and lines[a].strip() == '':
        a += 1
    while b > a and lines[b - 1].strip() == '':
        b -= 1
    return lines[a:b]


def process(path, apply):
    base = os.path.splitext(os.path.basename(path))[0]  # TaskNN
    with open(path, encoding='utf-8') as f:
        text = f.read()
    header, imports, types = parse(text)
    if len(types) < 2:
        return None  # не дробим
    doc = split_header(header)
    primary = choose_primary(types, base)
    folder = os.path.join(os.path.dirname(path), base)
    files = []
    for idx, t in enumerate(types):
        prefix = trim_blanks(t.get('prefix', []))
        body = prefix + t['lines'] if prefix else t['lines']
        out = []
        if idx == primary and doc:
            out += doc
            if imports: out.append('')
        out += imports
        if (out and out[-1].strip() != '') and body:
            out.append('')
        out += trim_blanks(body)
        content = '\n'.join(out).rstrip() + '\n'
        files.append((t['name'] + '.java', content))
    if apply:
        os.makedirs(folder, exist_ok=True)
        for name, content in files:
            with open(os.path.join(folder, name), 'w', encoding='utf-8', newline='\n') as f:
                f.write(content)
        os.remove(path)
    return (base, primary, [n for n, _ in files])


def main():
    args = [a for a in sys.argv[1:] if not a.startswith('--')]
    apply = '--apply' in sys.argv
    target = args[0]
    paths = []
    if os.path.isdir(target):
        for root, _, fs in os.walk(target):
            if os.path.basename(root) != 'practice':
                continue
            for fn in sorted(fs):
                if re.fullmatch(r'Task\d+\.java', fn):
                    paths.append(os.path.join(root, fn))
    else:
        paths = [target]
    done = 0
    for p in paths:
        r = process(p, apply)
        if r:
            done += 1
            base, primary, names = r
            print(f"{'APPLIED' if apply else 'PLAN'} {p}")
            print(f"  → {base}/  primary={names[primary]}  files={names}")
    print(f"--- {'разнесено' if apply else 'к разнесению'}: {done} файл(ов)")


if __name__ == '__main__':
    main()
