# Задача 02 — Модуль 30: Contract-first / OpenAPI

## ЗАДАНИЕ
Вынесите переиспользуемые схемы в `components` и используйте `$ref`.

## ВХОДНЫЕ ДАННЫЕ
Спецификация из задачи 01 + новые эндпоинты: `GET /orders/{id}` (возвращает Order), `GET /orders` (массив Order). Модель Order и типовая ошибка повторяются в нескольких местах.

## ТРЕБОВАНИЯ
- Вынесите схему `Order`, `CreateOrderRequest`, `Problem` (ошибка) в `components/schemas`.
- Вынесите типовой ответ `ValidationError` в `components/responses`.
- Замените inline-схемы на `$ref`.
- Покажите использование `$ref` в нескольких эндпоинтах (POST/GET by id/GET list).
- Спецификация валидна.

## ФОРМАТ РЕЗУЛЬТАТА
OpenAPI YAML с `components` и эндпоинтами через `$ref`.

## КРИТЕРИИ ПРИЁМКИ
- [ ] `components/schemas` содержит Order, CreateOrderRequest, Problem.
- [ ] `components/responses` содержит ValidationError.
- [ ] Эндпоинты ссылаются через `$ref` (нет дублирования моделей).
- [ ] Order переиспользован в POST (201), GET by id, GET list (массив через `items: $ref`).
- [ ] YAML валиден.
- [ ] Понятно, что изменение модели в одном месте отражается везде (DRY).

## ПОДСКАЗКА
`components/schemas/Order` + ссылка `$ref: '#/components/schemas/Order'`. Массив: `type: array, items: { $ref: ... }`. Типовая ошибка → `components/responses/ValidationError`, ссылка `$ref: '#/components/responses/ValidationError'`. DRY: одна модель — много мест. Проверьте резолвинг `$ref` в Swagger Editor.

---
<!-- Ваше решение ниже -->
