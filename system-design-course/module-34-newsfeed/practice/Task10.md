# Задача 10 — Модуль 34 (мини-проект): Полный HLD newsfeed

## ЗАДАНИЕ
Соберите полный high-level design ленты соцсети по фреймворку design-интервью. Синтез модуля.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте ленту новостей соцсети (Twitter/Instagram): ~300M DAU, read-heavy, есть знаменитости с десятками млн подписчиков, нужна низкая latency ленты». 45–60 минут.

## ТРЕБОВАНИЯ
Дизайн-документ по шагам:
1. **Требования** — FR/NFR, scope, модель согласованности (eventual).
2. **Estimation** — записи/чтения, fan-out (средний vs celebrity).
3. **Ключевое решение** — fan-out on write vs on read, trade-off.
4. **Celebrity problem + гибрид** — стратегия по типу автора.
5. **Модель данных** — посты, граф подписок, feed cache.
6. **High-level** — Post Service → очередь → fan-out воркеры → feed cache; чтение (push+pull); ранжирование.
7. **Узкие места** — celebrity, fan-out lag, hot content, размер кэша.

## ФОРМАТ РЕЗУЛЬТАТА
HLD из 7 разделов + диаграмма (потоки публикации и чтения) + расчёты estimation.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования: read-heavy, eventual consistency, scope зафиксирован.
- [ ] Estimation: записи ~7000/с, чтения ~35000/с, fan-out среднего ~200 vs celebrity ~50M.
- [ ] Ключевое решение: push vs pull разобрано с trade-off.
- [ ] Celebrity problem + гибрид (push для обычных, pull для celebrity, сборка ленты).
- [ ] Модель данных: посты, граф подписок (обе стороны), feed cache (окно N, ссылки).
- [ ] High-level: async fan-out через очередь, feed cache, чтение push+pull, ранжирование (хроно→ML).
- [ ] Узкие места: celebrity/fan-out lag/hot content/размер кэша; идемпотентность fan-out; связи с модулями 9/11/15/21/23/26.

## ПОДСКАЗКА
По фреймворку (модуль 03): требования→estimation→ключевая развилка push vs pull→celebrity/гибрид→модель данных→high-level (async fan-out+feed cache+чтение push/pull)→узкие места. Стержень — fan-out: push для большинства, pull для celebrity, доставка через async-очередь с идемпотентностью. Этим завершается под-батч a Части 8.

---
<!-- Ваше решение ниже -->
