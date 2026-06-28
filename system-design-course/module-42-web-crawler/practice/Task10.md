# Задача 10 — Модуль 42 (мини-проект): Полный HLD веб-краулера

## ЗАДАНИЕ
Соберите полный high-level design веб-краулера и поискового индекса по фреймворку design-интервью. Синтез модуля.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте веб-краулер для поисковика: обойти миллиарды страниц, уважая сайты, построить индекс, держать его свежим». 45–60 минут.

## ТРЕБОВАНИЯ
Дизайн-документ по шагам:
1. **Требования** — FR/NFR, противоречие масштаб vs politeness.
2. **Estimation** — скорость обхода, storage (страницы/индекс/граф).
3. **Архитектура** — frontier → fetcher → parser → dedup → storage/indexer.
4. **URL frontier** — приоритет + politeness (front/back queues).
5. **Politeness** — robots.txt + per-domain rate limit (модуль 16).
6. **Дедупликация + ловушки** — bloom (URL) + simhash (контент); лимиты/нормализация.
7. **Индекс/свежесть + распределённый обход + узкие места** — inverted index (модуль 17), recrawl; партиция по хосту (модуль 13).

## ФОРМАТ РЕЗУЛЬТАТА
HLD из 7 разделов + диаграмма (frontier→fetcher→parser→dedup→storage+indexer) + расчёт скорости/storage.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования: противоречие масштаб vs politeness; scope зафиксирован.
- [ ] Estimation: скорость обхода (страниц/с), storage (страницы blob + индекс + граф).
- [ ] Архитектура: frontier → fetcher → parser → dedup → storage/indexer (BFS).
- [ ] URL frontier: приоритет + politeness (front/back queues).
- [ ] Politeness: robots.txt + per-domain rate limit (модуль 16), trade-off vs throughput.
- [ ] Дедупликация: bloom filter (URL) + simhash (контент); защита от crawler traps.
- [ ] Inverted index (модуль 17) + recrawl/свежесть; распределённый обход партицией по хосту (модуль 13); связи 17/16/13/9/14/10.

## ПОДСКАЗКА
По фреймворку: требования (масштаб vs politeness) → estimation → архитектура (frontier→fetcher→parser→dedup→indexer) → frontier (приоритет+politeness) → politeness (robots+rate limit) → дедуп (bloom+simhash)+ловушки → индекс/recrawl+распределённый обход. Стержень: frontier приоритет+politeness; politeness vs throughput; дедуп двух видов; партиция по хосту.

---
<!-- Ваше решение ниже -->
