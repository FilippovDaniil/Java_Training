# Задача 01 — Модуль 46: Классификация задач по архетипам

## ЗАДАНИЕ
Классифицируйте список классических задач по архетипам (доминирующей оси).

## ВХОДНЫЕ ДАННЫЕ
Список задач: newsfeed, metrics-system, chat, Uber, YouTube, payment, web-crawler, job-scheduler, URL-shortener, ad-aggregation.

## ТРЕБОВАНИЯ
- Для каждой задачи определите доминирующий архетип (read-heavy / write-heavy-ingestion / real-time / geo / storage-heavy / correctness-critical / search-index / coordination-scheduling).
- Отметьте задачи со смешанными архетипами (несколько осей).
- Кратко обоснуйте каждую классификацию (что доминирует).
- Сделайте вывод: зачем классификация по архетипам.

## ФОРМАТ РЕЗУЛЬТАТА
Таблица: задача → архетип(ы) → обоснование → вывод о пользе классификации.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Каждой задаче присвоен доминирующий архетип с обоснованием.
- [ ] Read-heavy (newsfeed/URL-shortener), write-heavy (metrics/ad-aggregation), real-time (chat), geo (Uber), storage (YouTube), correctness (payment), search (web-crawler), coordination (job-scheduler).
- [ ] Отмечены смешанные архетипы (например, Uber = geo + real-time + correctness).
- [ ] Вывод: архетип определяет, какие блоки применять.

## ПОДСКАЗКА
newsfeed/URL-shortener — read-heavy; metrics/ad-aggregation — write-heavy; chat — real-time; Uber — geo+real-time+correctness; YouTube — storage; payment — correctness; web-crawler — search; job-scheduler — coordination. Архетип → блоки.

---
<!-- Ваше решение ниже -->
