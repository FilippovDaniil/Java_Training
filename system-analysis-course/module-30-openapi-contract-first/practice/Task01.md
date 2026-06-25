# Задача 01 — Модуль 30: Contract-first / OpenAPI

## ЗАДАНИЕ
Опишите эндпоинт в OpenAPI 3.x: path, метод, тело запроса, ответы со схемами.

## ВХОДНЫЕ ДАННЫЕ
**FoodExpress**, эндпоинт `POST /orders` (создать заказ): тело — clientId, restaurantId, список позиций (dishId, qty); ответ 201 — созданный заказ (id, status, total); ответ 422 — ошибка валидации.

## ТРЕБОВАНИЯ
- Опишите эндпоинт в OpenAPI 3.x (YAML): `paths`, `post`, `requestBody`, `responses`.
- Опишите схему запроса и схему ответа inline (вынос в components — задача 02).
- Укажите обязательные поля, типы, enum статуса.
- Опишите статусы 201 (с телом) и 422.
- Спецификация должна быть валидна в Swagger Editor.

## ФОРМАТ РЕЗУЛЬТАТА
Фрагмент OpenAPI YAML (info минимально + paths с POST /orders + схемы) + пометка о проверке в Swagger Editor.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Корректный `openapi: 3.0.x` + `info` (title, version).
- [ ] `paths./orders.post` с `requestBody` и `responses`.
- [ ] Схема запроса (clientId, restaurantId, items[]) с required и типами.
- [ ] Ответ 201 со схемой Order (id, status enum, total).
- [ ] Ответ 422 описан.
- [ ] YAML валиден (проверяемо в Swagger Editor).

## ПОДСКАЗКА
Минимум: `openapi`, `info` (title+version), `paths`. Эндпоинт = `paths: /orders: post:` с `requestBody.content.application/json.schema` и `responses` (201 со схемой Order, 422). Поля — через `properties`, обязательность — `required: [...]`, статус — `enum`. Проверьте на editor.swagger.io: не должно быть ошибок структуры.

---
<!-- Ваше решение ниже -->
