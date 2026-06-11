#!/usr/bin/env python3
"""Rewrite `module-NN-name` references in docs to the migrated `mNN_name`
package-folder names (same sanitize rule as migrate_inplace.py). Fixes both
display text and markdown link targets in README/PROGRESS files.

Usage:
    python tools/fix_doc_links.py <file.md> [<file.md> ...]            # dry run
    python tools/fix_doc_links.py <file.md> [...] --apply
"""
import argparse, re, sys

try:
    sys.stdout.reconfigure(encoding="utf-8")
except Exception:
    pass

TOKEN = re.compile(r"module-0*(\d+)(?:-([a-z0-9-]+))?")


def repl(m):
    num = int(m.group(1))
    rest = m.group(2)
    if rest:
        rest = rest.replace("-", "_").strip("_")
        return "m%02d_%s" % (num, rest)
    return "m%02d" % num


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("files", nargs="+")
    ap.add_argument("--apply", action="store_true")
    a = ap.parse_args()

    total = 0
    for path in a.files:
        with open(path, encoding="utf-8") as f:
            text = f.read()
        new, n = TOKEN.subn(repl, text)
        total += n
        print("  %4d  %s" % (n, path))
        if a.apply and n:
            with open(path, "w", encoding="utf-8", newline="\n") as f:
                f.write(new)
    print("\n%s: %d reference(s) %s" % (
        "APPLIED" if a.apply else "DRY RUN", total,
        "rewritten" if a.apply else "to rewrite"))


if __name__ == "__main__":
    main()
