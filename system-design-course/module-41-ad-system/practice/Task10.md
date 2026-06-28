# Задача 10 — Модуль 41 (мини-проект): Полный HLD рекламной системы

## ЗАДАНИЕ
Соберите полный high-level design рекламной системы по фреймворку design-интервью. Синтез модуля.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте рекламную систему: показ релевантных объявлений с низкой latency + подсчёт показов/кликов для аналитики и биллинга; миллиарды событий в день». 45–60 минут.

## ТРЕБОВАНИЯ
Дизайн-документ по шагам:
1. **Требования** — FR/NFR, два пути (serving vs aggregation).
2. **Estimation** — event QPS (сотни тыс/с), нагрузка serving.
3. **Ad serving** — low-latency, таргетинг/ранжирование, кэш, фильтр бюджета.
4. **Event ingestion** — collector → очередь (Kafka) → стрим-процессоры.
5. **Агрегация** — async по временным окнам, pre-aggregation (не синхронный инкремент).
6. **Exactly-once + OLAP** — дедуп по event_id (модуль 23), click fraud, OLAP + Lambda/Kappa.
7. **Бюджет/pacing + узкие места** — near-real-time учёт, pacing; event throughput/hot ads/accuracy vs latency.

## ФОРМАТ РЕЗУЛЬТАТА
HLD из 7 разделов + диаграмма (ad server + event collector → очередь → стрим-агрегаторы → OLAP → дашборды/биллинг/pacing) + расчёт event QPS.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования: два пути (serving low-latency vs aggregation write-heavy); scope зафиксирован.
- [ ] Estimation: сотни тыс. событий/с; нагрузка serving + latency-бюджет.
- [ ] Ad serving: кандидаты/таргетинг/ранжирование/кэш, фильтр исчерпанных кампаний.
- [ ] Event ingestion: collector → очередь Kafka (модуль 15) → стрим-процессоры.
- [ ] Агрегация: async по окнам + pre-aggregation, не синхронный инкремент (модуль 26).
- [ ] Exactly-once: дедуп по event_id (модуль 23) + click fraud; OLAP (модуль 18) + Lambda/Kappa (модуль 20).
- [ ] Бюджет/pacing (near-real-time учёт); узкие места (event throughput/hot ads/accuracy vs latency); связи 15/20/18/26/23/9.

## ПОДСКАЗКА
По фреймворку: требования (два пути) → estimation (event QPS) → ad serving (low-latency, кэш) → event ingestion (очередь) → async-агрегация по окнам → exactly-once/дедуп + OLAP/Lambda-Kappa → бюджет/pacing + узкие места. Стержень: два пути; счётчики не инкрементировать синхронно (очередь→async-агрегация); клик считать один раз (дедуп).

---
<!-- Ваше решение ниже -->
