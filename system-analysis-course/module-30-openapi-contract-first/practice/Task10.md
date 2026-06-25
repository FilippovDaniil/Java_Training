# Задача 10 (мини-проект) — Модуль 30: Contract-first / OpenAPI

## ЗАДАНИЕ
Напишите валидную OpenAPI 3.x спецификацию фрагмента API FoodExpress (оформление заказа) с components, security, ошибками и пагинацией. Синтез теории модуля.

## ВХОДНЫЕ ДАННЫЕ
**FoodExpress**, API заказов: `POST /orders` (создать, idempotency-key), `GET /orders/{id}`, `GET /orders` (список с пагинацией и фильтрами), `PATCH /orders/{id}` (смена статуса). JWT Bearer для всех эндпоинтов. Ошибки — RFC 7807.

## ТРЕБОВАНИЯ
Сдайте OpenAPI YAML:
1. **info + servers** (версия в URI `/v1`).
2. **paths** — 4 эндпоинта с методами, параметрами, телами, ответами и статусами (201/200/204/4xx).
3. **components/schemas** — Order, CreateOrderRequest, StatusPatch, Problem (через `$ref`).
4. **components/responses** — типовые ошибки (401/403/404/409/422) на базе Problem.
5. **components/parameters** — пагинация/фильтры для `GET /orders`.
6. **security** — bearerAuth (JWT), применён.
7. **idempotency** — заголовок Idempotency-Key для POST.
Спецификация должна быть валидна в Swagger Editor.

## ФОРМАТ РЕЗУЛЬТАТА
Полная (для фрагмента) OpenAPI YAML-спецификация, валидная в editor.swagger.io.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Валидный OpenAPI 3.x: info (version), servers (с `/v1`).
- [ ] 4 эндпоинта с корректными методами/статусами (201+Location, 204, 4xx).
- [ ] Схемы в components, переиспользование через `$ref` (нет дублей).
- [ ] Типовые ошибки 401/403/404/409/422 (RFC 7807, problem+json).
- [ ] Пагинация/фильтры как параметры (вынесены в components/parameters).
- [ ] securitySchemes bearerAuth + применение security.
- [ ] Idempotency-Key как header-параметр для POST.
- [ ] Спецификация проходит валидацию без ошибок.

## ПОДСКАЗКА
Синтез: эндпоинт (зад.01), components/$ref (02), security+ошибки (03), версия в URI (04), пагинация (07). Главные проверки middle+: валидная структура, DRY через components, корректные статусы, security и ошибки описаны, idempotency-key для POST, версия в URI. Обязательно прогнать через editor.swagger.io — спека без ошибок. Это вход для генерации моков/клиента/тестов (модуль 35).

---
<!-- Ваше решение ниже -->
