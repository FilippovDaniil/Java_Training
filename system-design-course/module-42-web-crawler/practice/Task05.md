# Задача 05 — Модуль 42: Politeness

## ЗАДАНИЕ
Спроектируйте механизм politeness, отличающий краулер от DDoS.

## ВХОДНЫЕ ДАННЫЕ
Краулер с высоким суммарным throughput не должен перегружать отдельные сайты. Сайты задают правила обхода в robots.txt.

## ТРЕБОВАНИЯ
- Опишите уважение robots.txt (Disallow, crawl-delay).
- Спроектируйте per-domain rate limit (модуль 16) — лимит на хост, не глобальный.
- Опишите back-off при ошибках/5xx сервера.
- Сформулируйте trade-off politeness vs throughput (нельзя ускорить один домен).

## ФОРМАТ РЕЗУЛЬТАТА
robots.txt → per-domain rate limit → back-off → trade-off politeness vs throughput.

## КРИТЕРИИ ПРИЁМКИ
- [ ] robots.txt: уважать Disallow и crawl-delay.
- [ ] Per-domain rate limit (модуль 16): лимит на ОДИН хост, не глобальный.
- [ ] Back-off при ошибках/5xx (не добивать падающий сайт).
- [ ] Сформулирован trade-off: politeness ограничивает per-domain → throughput через параллелизм по доменам.

## ПОДСКАЗКА
robots.txt (Disallow/crawl-delay), per-domain rate limit (модуль 16, не глобальный), back-off при 5xx. Trade-off: нельзя ускорить один домен; throughput — параллелизм по многим.

---
<!-- Ваше решение ниже -->
