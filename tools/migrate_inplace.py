#!/usr/bin/env python3
"""Make course practice files natively runnable in IntelliJ WITHOUT moving them
out of their module (code stays next to theory.md). Scheme C4b:

  java-course/module-01-intro-first-program/practice/Task01.java
      ->  java-course/m01_intro_first_program/practice/Task01.java
          + `package m01_intro_first_program.practice;`

  multi-file task folder  .../practice/Task30/  ->  .../practice/task30/
          + `package m30_....practice.task30;`

The course dirs (java-course, patterns-architecture, algorithms-course) are
declared as source roots in build.gradle, so the package == the path under the
course dir. Module folder names are globally unique across courses (verified),
so no package collisions.

Idempotent: a module already named mNN_* is processed in place; a file that
already has a `package` line is left alone. Default = DRY RUN; --apply executes.
Migrate one batch at a time with --course / --module.

Usage:
    python tools/migrate_inplace.py --course java-course                       # dry run, whole course
    python tools/migrate_inplace.py --course java-course --module module-01-intro-first-program --apply
"""
import argparse, os, re, subprocess, sys

try:
    sys.stdout.reconfigure(encoding="utf-8")
except Exception:
    pass

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
COURSES = ["java-course", "patterns-architecture", "algorithms-course"]


def sanitize_module(name):
    m = re.match(r"module-0*(\d+)-(.+)$", name)
    if m:
        rest = re.sub(r"[^0-9a-zA-Z]+", "_", m.group(2)).strip("_").lower()
        return "m%02d_%s" % (int(m.group(1)), rest)
    m2 = re.match(r"module-0*(\d+)$", name)
    if m2:
        return "m%02d" % int(m2.group(1))
    raise ValueError(name)


def sanitize_seg(name):
    s = re.sub(r"[^0-9a-zA-Z]+", "_", name).strip("_").lower()
    if s and s[0].isdigit():
        s = "t_" + s
    return s


def canonical(modname):
    return modname if re.match(r"m\d+", modname) else sanitize_module(modname)


def mod_num(modname):
    m = re.match(r"(?:module-|m)0*(\d+)", modname)
    return int(m.group(1)) if m else None


def has_package_line(path):
    with open(path, encoding="utf-8") as f:
        for line in f:
            s = line.strip()
            if not s or s.startswith(("//", "/*", "*")):
                continue
            return s.startswith("package ")
    return False


def prepend_package(path, pkg):
    with open(path, encoding="utf-8") as f:
        body = f.read()
    with open(path, "w", encoding="utf-8", newline="\n") as f:
        f.write("package %s;\n\n" % pkg + body)


def git_mv(src, dst):
    return subprocess.run(["git", "-C", ROOT, "mv", "-f", src, dst],
                          capture_output=True, text=True).returncode == 0


def process_module(course, modname, apply):
    """Returns (renames, pkgs) counts. Renames module dir + Task* subdirs,
    then prepends package to each practice .java."""
    course_dir = os.path.join(ROOT, course)
    new_mod = canonical(modname)
    renames = pkgs = 0

    # 1. module folder rename
    cur_mod_dir = os.path.join(course_dir, modname)
    new_mod_dir = os.path.join(course_dir, new_mod)
    if modname != new_mod:
        print("  rename  %s/%s  ->  %s" % (course, modname, new_mod))
        renames += 1
        if apply:
            git_mv(cur_mod_dir, new_mod_dir)
    base = new_mod_dir if (apply and modname != new_mod) else cur_mod_dir

    practice = os.path.join(base, "practice")
    if not os.path.isdir(practice):
        return renames, pkgs

    # 2. rename Task* subfolders (multi-file tasks) to lowercase
    for sub in sorted(os.listdir(practice)):
        subp = os.path.join(practice, sub)
        if os.path.isdir(subp):
            low = sanitize_seg(sub)
            if low != sub:
                print("  rename  %s/%s/practice/%s  ->  %s" % (course, new_mod, sub, low))
                renames += 1
                if apply:
                    git_mv(subp, os.path.join(practice, low))

    # 3. prepend package to every practice .java (compute pkg from final layout)
    practice_final = os.path.join(course_dir, new_mod, "practice")
    scan = practice_final if (apply) else practice
    for dp, _dns, fns in os.walk(scan):
        for fn in fns:
            if not fn.endswith(".java"):
                continue
            fpath = os.path.join(dp, fn)
            # final relative dir under course dir, sanitized
            rel = os.path.relpath(dp, scan)             # '.', 'Task30', 'task30'
            sub_segs = [] if rel == "." else [sanitize_seg(s) for s in rel.split(os.sep)]
            pkg = ".".join([new_mod, "practice"] + sub_segs)
            if has_package_line(fpath):
                continue
            print("    pkg  %s  ->  package %s;" % (
                os.path.relpath(fpath, ROOT), pkg))
            pkgs += 1
            if apply:
                prepend_package(fpath, pkg)
    return renames, pkgs


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--course", choices=COURSES)
    ap.add_argument("--module", help="module folder (original 'module-NN-...' or migrated 'mNN_...')")
    ap.add_argument("--from", dest="frm", type=int, help="first module number in batch (inclusive)")
    ap.add_argument("--to", type=int, help="last module number in batch (inclusive)")
    ap.add_argument("--apply", action="store_true")
    a = ap.parse_args()

    courses = [a.course] if a.course else COURSES
    total_r = total_p = 0
    for course in courses:
        cdir = os.path.join(ROOT, course)
        if not os.path.isdir(cdir):
            continue
        mods = []
        for d in sorted(os.listdir(cdir)):
            if not os.path.isdir(os.path.join(cdir, d)):
                continue
            if re.match(r"module-\d+", d) or re.match(r"m\d+", d):
                mods.append(d)
        if a.module:
            want = canonical(a.module)
            mods = [m for m in mods if canonical(m) == want]
        if a.frm is not None:
            mods = [m for m in mods if (mod_num(m) or 0) >= a.frm]
        if a.to is not None:
            mods = [m for m in mods if (mod_num(m) or 0) <= a.to]
        for m in mods:
            r, p = process_module(course, m, a.apply)
            total_r += r
            total_p += p

    print("\n%s: %d folder rename(s), %d package line(s) %s" % (
        "APPLIED" if a.apply else "DRY RUN",
        total_r, total_p, "added" if a.apply else "to add"))


if __name__ == "__main__":
    main()
