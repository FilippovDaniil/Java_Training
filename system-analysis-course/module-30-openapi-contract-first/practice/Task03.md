# Задача 03 — Модуль 30: Contract-first / OpenAPI

## ЗАДАНИЕ
Опишите ошибки (RFC 7807) и security в OpenAPI-спецификации.

## ВХОДНЫЕ ДАННЫЕ
**FoodExpress** API: все эндпоинты (кроме публичного каталога) требуют JWT Bearer-токен. Возможные ошибки: 401 (нет токена), 403 (нет прав), 404, 422 (валидация), 409 (конфликт статуса). Формат ошибок — RFC 7807 (problem+json).

## ТРЕБОВАНИЯ
- Опишите схему `Problem` (RFC 7807: type, title, status, detail, instance) в components.
- Опишите типовые ответы об ошибках (401/403/404/409/422) в `components/responses`.
- Опишите `securitySchemes` (bearerAuth, JWT) и примените `security`.
- Покажите публичный эндпоинт (каталог) без security и защищённый — с security.
- Спецификация валидна.

## ФОРМАТ РЕЗУЛЬТАТА
OpenAPI YAML: схема Problem + responses (ошибки) + securitySchemes + применение security (глобально/на эндпоинт).

## КРИТЕРИИ ПРИЁМКИ
- [ ] Схема Problem по RFC 7807 (type, title, status, detail, instance), media-type `application/problem+json`.
- [ ] Типовые ответы 401/403/404/409/422 в components/responses через `$ref` на Problem.
- [ ] `securitySchemes.bearerAuth` (type: http, scheme: bearer, bearerFormat: JWT).
- [ ] `security` применён глобально или на эндпоинты.
- [ ] Публичный эндпоинт без security (`security: []`), защищённый — с security.
- [ ] YAML валиден.

## ПОДСКАЗКА
Ошибки: схема Problem (RFC 7807, модуль 29) + `components/responses` для 401/403/404/409/422 с `application/problem+json`. Security: `components/securitySchemes/bearerAuth: {type: http, scheme: bearer, bearerFormat: JWT}`, применить через `security: [bearerAuth: []]` (глобально) и переопределить `security: []` на публичных. Реальная проверка прав — модуль 34.

---
<!-- Ваше решение ниже -->
