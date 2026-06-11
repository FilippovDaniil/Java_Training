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
- [x] **B4** java-course 41–60 — 64 rename, 250 pkg, compile clean (Spring deps OK)
- [x] **B5** java-course 61–80 — 157 rename, 506 pkg, compile clean
- [x] **B6** java-course 81–100 — 149 rename, 501 pkg, 0 структурных. NB: m99 Task06 импортирует oauth2.jwt/jose (нет в deps) → см. B11
- [x] **B7** java-course 101–118 — 86 rename, 320 pkg, compile clean. ✅ java-course мигрирован ПОЛНОСТЬЮ (0 module-* осталось)
- [x] **B8** patterns-architecture 01–13 — 103 rename, 465 pkg, compile clean
- [x] **B9** patterns-architecture 14–26 — 104 rename, 378 pkg, compile clean. ✅ patterns-architecture ПОЛНОСТЬЮ
- [x] **B10** algorithms-course 01–25 — 67 rename, 218 pkg, compile clean. ✅ algorithms-course ПОЛНОСТЬЮ. 🎉 ВСЕ 3 КУРСА мигрированы
- [x] **B11** Финал — toolchain 21, доки (390 ссылок), run.ps1 OK, clean compile 0 структурных (см. §8)

## 8. Финал (B11) — ВЫПОЛНЕНО

- [x] Пин JDK 21 в `build.gradle` (`java.toolchain.languageVersion = 21`) — clean compile прошёл, Gradle нашёл toolchain.
- [x] Доки: `tools/fix_doc_links.py` переписал 390 ссылок `module-NN-*` → `mNN_*` в `java-course/README.md`, `patterns-architecture/README.md` + `PROGRESS.md`, `algorithms-course/README.md` + `PROGRESS.md`. Корневой `README.md` и `LEARNING-METHODOLOGY.md` — 0 ссылок (не трогали).
- [x] `run.ps1`: поиск резолвит `mNN_*` пути (проверено на m24).
- [x] Финальный `gradlew clean compileJava` — exit 0, **0 структурных ошибок** по всем 3 курсам.
- [ ] **ОСТАЁТСЯ (online):** `m99_…/practice/Task06.java` импортирует `org.springframework.security.oauth2.jwt/jose` — нужен dep `spring-security-oauth2-jose`/`spring-boot-starter-oauth2-resource-server`. Не добавлено: BOM 3.4.1 хочет 6.4.x, в офлайн-кеше только 6.2.4 → добавить при наличии сети. 1 задача из ~2986.
- [ ] (опц.) Прозаические упоминания `module-NN` внутри `theory.md` (~390) — не критично, можно прогнать тем же `fix_doc_links.py` при желании.

## 9. Откат

Всё через git. Откатить батч до коммита: `git restore --staged --worktree -- <курс>`.
Откатить закоммиченный батч: `git revert <hash>` или `git reset --hard <hash>`.

## 10. Объём

169 модулей, ~2986 `.java`: java-course 118/1925, patterns 26/843, algorithms 25/218.
