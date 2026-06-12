# -*- coding: utf-8 -*-
"""Конвертирует символы Box Drawing (U+2500-U+257F) в чистый ASCII (+ - |)
во всех *.md файлах репозитория. Альтернатива псевдографике, которая
"съезжает" в шрифтах с ambiguous-width. Идемпотентно. LF/UTF-8 сохраняются.

Маппинг по unicode-именам:
  только горизонталь (HORIZONTAL/LEFT/RIGHT)  -> '-'
  только вертикаль   (VERTICAL/UP/DOWN)       -> '|'
  углы/тройники/кресты/дуги (и H, и V)        -> '+'
  диагонали                                   -> '/' '\\' 'X'
"""
import glob, sys, unicodedata, io

def build_map():
    m = {}
    for cp in range(0x2500, 0x2580):
        ch = chr(cp)
        try:
            name = unicodedata.name(ch)
        except ValueError:
            continue
        if "DIAGONAL" in name:
            if "UPPER RIGHT TO LOWER LEFT" in name: m[ch] = "/"
            elif "UPPER LEFT TO LOWER RIGHT" in name: m[ch] = "\\"
            elif "CROSS" in name: m[ch] = "X"
            else: m[ch] = "+"
            continue
        has_h = ("HORIZONTAL" in name) or ("LEFT" in name) or ("RIGHT" in name)
        has_v = ("VERTICAL" in name) or ("UP" in name) or ("DOWN" in name)
        if has_h and has_v: m[ch] = "+"
        elif has_h: m[ch] = "-"
        elif has_v: m[ch] = "|"
    return m

def main():
    BOX = build_map()
    table = {ord(k): v for k, v in BOX.items()}
    files = glob.glob("**/*.md", recursive=True)
    changed, total_repl = [], 0
    for f in files:
        raw = io.open(f, "rb").read()
        text = raw.decode("utf-8")
        cnt = sum(text.count(ch) for ch in BOX)
        if not cnt:
            continue
        new = text.translate(table)
        assert not any(ch in new for ch in BOX), f"остались box-символы в {f}"
        io.open(f, "w", encoding="utf-8", newline="").write(new)
        changed.append((cnt, f)); total_repl += cnt
    changed.sort(reverse=True)
    print(f"Изменено файлов: {len(changed)} / {len(files)} (.md всего)")
    print(f"Всего заменено символов: {total_repl}")
    for c, f in changed[:15]:
        print(f"  {c:5d}  {f}")
    if len(changed) > 15:
        print(f"  … ещё {len(changed)-15} файлов")

if __name__ == "__main__":
    sys.stdout.reconfigure(encoding="utf-8")
    main()
