#!/usr/bin/env python3
"""Аудит компиляции разнесённых папок TaskNN/ в java-course.
Компилирует каждую папку javac (опц. с classpath) и классифицирует:
  OK      — скомпилировалось без ошибок;
  STRUCT  — синтаксические/структурные ошибки → возможный дефект дробления;
  OTHER   — прочие ошибки (отсутствие библиотек / student-TODO заглушки и т.п.).
Печатает сводку, топ отсутствующих пакетов и список STRUCT-папок.

Usage: python compile_audit.py [java-course] [--cp <classpath-file>]
"""
import os, re, subprocess, sys, tempfile, collections
try:
    sys.stdout.reconfigure(encoding='utf-8')
except Exception:
    pass

args = [a for a in sys.argv[1:] if not a.startswith('--')]
ROOT = args[0] if args else 'java-course'
CP = None
if '--cp' in sys.argv:
    cpfile = sys.argv[sys.argv.index('--cp') + 1]
    CP = open(cpfile, encoding='utf-8').read().strip()

# Явно СТРУКТУРНЫЕ/синтаксические ошибки (признак плохого дробления):
STRUCT_PAT = re.compile(
    r'should be declared in a file named'
    r'|duplicate class'
    r'|reached end of file while parsing'
    r'|class, interface, enum, or record expected'
    r'|illegal start of'
    r"|'\{' expected|'\}' expected|';' expected|'\)' expected"
    r'|<identifier> expected'
    r'|class .* is public, should be declared'
)
PKG_MISSING = re.compile(r'package ([\w.]+) does not exist')

folders = []
for dp, dns, fns in os.walk(ROOT):
    if os.path.basename(os.path.dirname(dp)) == 'practice' and re.fullmatch(r'Task\d+', os.path.basename(dp)):
        if any(f.endswith('.java') for f in fns):
            folders.append(dp)
folders.sort()

ok = struct = other = 0
struct_hits = []
missing_pkgs = collections.Counter()
only_symbol = 0  # папки, где ошибки = только 'cannot find symbol' (нет missing-package) — вероятно student-TODO
base_javac = ['javac', '-encoding', 'UTF-8']
if CP:
    base_javac += ['-cp', CP]

for d in folders:
    javas = [os.path.join(d, f) for f in os.listdir(d) if f.endswith('.java')]
    with tempfile.TemporaryDirectory() as out:
        r = subprocess.run(base_javac + ['-d', out, *javas],
                           capture_output=True, text=True, encoding='utf-8', errors='replace')
    if r.returncode == 0:
        ok += 1
        continue
    err_lines = [l for l in (r.stderr or '').splitlines() if ': error:' in l]
    struct_lines = [l for l in err_lines if STRUCT_PAT.search(l)]
    pkgs = [m.group(1) for l in err_lines for m in [PKG_MISSING.search(l)] if m]
    if struct_lines:
        struct += 1
        struct_hits.append((d, struct_lines[:6]))
    else:
        other += 1
    for p in pkgs:
        missing_pkgs['.'.join(p.split('.')[:3])] += 1
    if err_lines and not pkgs and all('cannot find symbol' in l for l in err_lines):
        only_symbol += 1

print(f"Папок TaskNN/: {len(folders)}   classpath: {'ДА ('+str(len(CP.split(os.pathsep)))+' jars)' if CP else 'нет'}")
print(f"  OK     (скомпилировалось чисто):                 {ok}")
print(f"  STRUCT (структурные/синтаксические) !!!:          {struct}")
print(f"  OTHER  (отсутствие библиотек / TODO-заглушки):    {other}")
print(f"    из них: ошибки = только 'cannot find symbol'    {only_symbol}  (вероятно student-TODO)")
if missing_pkgs:
    print("\nТоп отсутствующих пакетов (что ещё не в classpath):")
    for p, c in missing_pkgs.most_common(15):
        print(f"   {c:4}  {p}")
if struct_hits:
    print("\n=== STRUCT — структурные дефекты (требуют внимания) ===")
    for d, lines in struct_hits:
        print(f"\n[{d}]")
        for l in lines:
            print("   " + l.strip())
else:
    print("\nСтруктурных дефектов дробления НЕ найдено.")
