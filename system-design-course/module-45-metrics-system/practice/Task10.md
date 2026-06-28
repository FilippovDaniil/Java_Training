# Задача 10 — Модуль 45 (мини-проект): Полный HLD системы метрик

## ЗАДАНИЕ
Соберите полный high-level design системы метрик и мониторинга по фреймворку design-интервью. Синтез модуля и завершение Части 8.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте систему метрик и мониторинга (Prometheus/Datadog): приём миллионов метрик/с, хранение, запросы по диапазонам, алертинг, дашборды». 45–60 минут.

## ТРЕБОВАНИЯ
Дизайн-документ по шагам:
1. **Требования** — FR/NFR, природа (write-heavy time-series).
2. **Estimation** — write QPS (хосты×метрики×частота), storage + эффект сжатия.
3. **Модель данных** — time-series (метрика + labels + ts + value), серия.
4. **High cardinality** — взрыв labels, правило выбора labels.
5. **Ingestion + TSDB** — push vs pull; TSDB со сжатием (delta-of-delta + XOR).
6. **Downsampling/retention** — rollups, retention tiers.
7. **Запросы/алертинг + масштаб/узкие места** — диапазоны+агрегация, alert manager (дедуп); шардирование по серии, cardinality.

## ФОРМАТ РЕЗУЛЬТАТА
HLD из 7 разделов + диаграмма (агенты push/pull → ingestion → буфер → TSDB → query engine + alerting → дашборды) + расчёт write QPS.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования: природа write-heavy time-series; scope зафиксирован.
- [ ] Estimation: write QPS (хосты×метрики×частота) ~млн/с, storage + эффект сжатия.
- [ ] Модель: time-series (метрика + labels + ts + value), серия.
- [ ] High cardinality: взрыв от unbounded labels; правило выбора labels (главная ловушка).
- [ ] Ingestion push vs pull; TSDB со сжатием time-series (delta-of-delta + XOR, append-heavy).
- [ ] Downsampling/rollups + retention tiers.
- [ ] Запросы (диапазон+агрегация) + алертинг (alert manager/дедуп); масштаб (шардирование по серии — модуль 14); связи 28/18/15/14/36/12.

## ПОДСКАЗКА
По фреймворку: требования (write-heavy time-series) → estimation → модель (метрика+labels+ts+value) → high cardinality (главная ловушка) → ingestion push/pull + TSDB/сжатие → downsampling/retention → запросы/алертинг + масштаб. Стержень: time-series как класс данных; high cardinality — ловушка №1; TSDB со сжатием; downsampling для retention. Этим завершается Часть 8.

---
<!-- Ваше решение ниже -->
