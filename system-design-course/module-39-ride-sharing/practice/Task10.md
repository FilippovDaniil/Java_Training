# Задача 10 — Модуль 39 (мини-проект): Полный HLD ride-sharing

## ЗАДАНИЕ
Соберите полный high-level design сервиса такси по фреймворку design-интервью. Синтез модуля.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте Uber/Lyft: rider запрашивает поездку, система матчит ближайшего водителя, водители шлют локацию в реальном времени, трекинг и оплата; миллионы водителей, сотни городов». 45–60 минут.

## ТРЕБОВАНИЯ
Дизайн-документ по шагам:
1. **Требования** — FR/NFR, главная ось (write-heavy real-time гео-поток).
2. **Estimation** — write QPS локаций (сотни тыс/с), поездки, гео-запросы.
3. **Geo-индекс** — geohash/quadtree/S2/H3 для nearby (модуль 17).
4. **Location pipeline** — эфемерные позиции в in-memory/Redis GEO (не durable БД).
5. **Матчинг** — атомарный захват (один водитель ↔ одна поездка) + идемпотентность (модуль 23).
6. **Состояние поездки** — durable state machine в БД (контраст с локациями).
7. **Real-time/ETA/surge + узкие места** — WebSocket (модуль 35), surge по зонам; write throughput/hot regions/matching latency, шард гео по региону.

## ФОРМАТ РЕЗУЛЬТАТА
HLD из 7 разделов + диаграмма (rider/driver ↔ gateway/WebSocket → location/matching/trip/pricing → geo-index/Redis + БД + очередь) + расчёт location-throughput.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования: главная ось write-heavy real-time гео-поток; scope зафиксирован.
- [ ] Estimation: сотни тыс. location-апдейтов/с; поездки/гео-запросы.
- [ ] Geospatial index (geohash/quadtree/S2/H3) для nearby (модуль 17), учтена граничная проблема.
- [ ] Location pipeline: эфемерные позиции в in-memory/Redis GEO, не durable БД; история — async (модуль 20).
- [ ] Матчинг: атомарный захват (один водитель ↔ одна поездка) + идемпотентность (модуль 23).
- [ ] Поездка: durable state machine в БД (контраст с локациями).
- [ ] WebSocket/push для трекинга (модуль 35); surge по зонам; узкие места (write throughput/hot regions/matching latency) + шард гео по региону (модуль 13); связи 17/35/23/13/20/26.

## ПОДСКАЗКА
По фреймворку: требования (write-heavy гео) → estimation (location QPS) → geo-индекс → location pipeline (Redis GEO, эфемерно) → матчинг (атомарный захват + идемпотентность) → durable поездка (state machine) → real-time/ETA/surge + узкие места (шард гео). Стержень: эфемерные локации (in-memory) vs durable-поездка (БД); geo-индекс для nearby; один водитель ↔ одна поездка.

---
<!-- Ваше решение ниже -->
