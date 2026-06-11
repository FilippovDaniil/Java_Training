# Нативный запуск Task'ов в IntelliJ — план миграции (C4b)

Живой документ. Отмечай `[x]` по мере выполнения батчей. Безопасно продолжать в новой сессии: статус — в чек-листе ниже, история — в `git log`.

---

## 1. Цель

Чтобы у каждого `TaskNN.java` слева был **нативный зелёный ▶** (Run), и вывод
показывался в консоли IDEA — при этом код остаётся **рядом с `theory.md`**
внутри своего модуля.

## 2. ⚠️ Обязательная настройка IDE (иначе красная стандартная библиотека)

IntelliJ 2025.2 **не до конца понимает JDK 25** → `System.out.println` светится
красным. Использовать **JDK 21 (LTS)**:

- Settings → Build Tools → **Gradle → Gradle JVM = 21**
- Project Structure → **Project → SDK = 21**, Language level = 21
- затем **Reload All Gradle Projects**

(JDK 25 годится только для `run.ps1` из консоли — там компилятор формат понимает.)

## 3. Как запускать Task

| Способ | Как | Когда |
|--------|-----|-------|
| **Нативный ▶** (основной) | клик по зелёной стрелке у `main` | после миграции модуля |
| **External Tool** (резерв) | правый клик → External Tools → Run Java File, либо хоткей | работает и до миграции, и для Spring-classpath |
| **Терминал** | `.\run.ps1 <модуль> <Task>` | вне IDEA |

## 4. Схема миграции C4b

```
java-course/module-01-intro-first-program/practice/Task01.java
   ->  java-course/m01_intro_first_program/practice/Task01.java
       + package m01_intro_first_program.practice;
```
- Папки модулей `module-NN-...` → `mNN_...` (валидный идентификатор пакета).
- Многофайловые `…/practice/TaskKK/` → `…/practice/taskKK/`, пакет `….practice.taskKK`.
- `theory.md` остаётся в папке модуля.
- Курсы (`java-course`, `patterns-architecture`, `algorithms-course`) объявлены
  source root'ами в `build.gradle`; ещё не мигрированные `module-*` исключены
  (`exclude 'module-*/**'`) — поэтому во время раскатки нет конфликтов дубликатов.

## 5. Скрипт миграции

```powershell
# dry-run (показать план, ничего не менять):
python tools/migrate_inplace.py --course java-course --from 2 --to 20
# применить:
python tools/migrate_inplace.py --course java-course --from 2 --to 20 --apply
```
Флаги: `--course`, `--module <имя>`, `--from N --to N` (диапазон номеров), `--apply`.
Идемпотентно: уже мигрированные модули/файлы пропускаются.

## 6. Процедура батча (SOP)

1. `python tools/migrate_inplace.py --course X --from A --to B` — посмотреть план.
2. `… --apply` — применить.
3. `gradlew compileJava` — зелёная сборка (Claude; ты в это время не синкаешь IDE).
4. `git add -A && git commit -m "native-run: батч ..."`.
5. **Ты:** Reload Gradle в IDEA → проверь ▶ на 1–2 задачах из батча.

## 7. Чек-лист батчей

- [x] **B1** java-course m01 (пилот) — структура+компиляция OK; ▶ подтверждён
- [x] **B2** java-course 02–20 — 51 rename, 169 pkg, compile clean (170 классов)
- [x] **B3** java-course 21–40 — 68 rename, 172 pkg, compile clean
- [ ] **B4** java-course 41–60
- [ ] **B5** java-course 61–80
- [ ] **B6** java-course 81–100
- [ ] **B7** java-course 101–118
- [ ] **B8** patterns-architecture 01–13
- [ ] **B9** patterns-architecture 14–26
- [ ] **B10** algorithms-course 01–25
- [ ] **B11** Финал: доки + `run.ps1` поиск + JDK 21 в build.gradle (см. §8)

## 8. Финал (B11)

- [ ] Пин JDK 21 в `build.gradle` (toolchain) — чтобы IDE/консоль всегда совпадали.
- [ ] `run.ps1`: расширить поиск (пути уже не `module-*`, а `mNN_*`).
- [ ] Доки на новые пути: `README.md` (корень + курсы), `*/PROGRESS.md`,
      `LEARNING-METHODOLOGY.md`, память (`MEMORY.md` и связанные).
- [ ] Снести `tools/External Tools.xml`/`migrate_inplace.py`? — нет, оставить как инструменты.
- [ ] Финальный `gradlew compileJava` по всем трём курсам.

## 9. Откат

Всё через git. Откатить батч до коммита: `git restore --staged --worktree -- <курс>`.
Откатить закоммиченный батч: `git revert <hash>` или `git reset --hard <hash>`.

## 10. Объём

169 модулей, ~2986 `.java`: java-course 118/1925, patterns 26/843, algorithms 25/218.
