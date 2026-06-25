# Задача 02 — Модуль 23: Сети

## ЗАДАНИЕ
Разберите HTTP-запрос и ответ по составляющим: определите метод, URI, заголовки, тело, статус.

## ВХОДНЫЕ ДАННЫЕ
Реальный обмен (FoodExpress):
```
POST /api/v1/orders HTTP/1.1
Host: api.foodexpress.ru
Authorization: Bearer eyJ...
Content-Type: application/json
Idempotency-Key: 7f3a-...

{"clientId": 1, "items": [{"dishId": 10, "qty": 2}]}

---ОТВЕТ---
HTTP/1.1 201 Created
Content-Type: application/json
Location: /api/v1/orders/1024

{"id": 1024, "status": "created", "total": 600}
```

## ТРЕБОВАНИЯ
- Разберите запрос: метод, URI, ключевые заголовки и их роль, тело.
- Разберите ответ: статус-код и его смысл, заголовки (Location), тело.
- Объясните роль `Authorization`, `Content-Type`, `Idempotency-Key`, `Location`.
- Объясните, почему здесь именно 201, а не 200.

## ФОРМАТ РЕЗУЛЬТАТА
Разбор запроса и ответа по элементам + таблица `Заголовок | Роль`.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Метод POST, URI `/api/v1/orders`, тело — JSON заказа.
- [ ] Статус 201 Created (создан ресурс) с пояснением, почему не 200.
- [ ] `Authorization: Bearer` — аутентификация (токен).
- [ ] `Content-Type` — формат тела; `Idempotency-Key` — защита от дублей.
- [ ] `Location` — URI созданного ресурса.
- [ ] Роль каждого заголовка объяснена.

## ПОДСКАЗКА
HTTP-запрос: метод + URI + заголовки + тело. Ответ: статус + заголовки + тело. 201 Created = создан новый ресурс (с Location на него), в отличие от 200 OK (общий успех). Idempotency-Key защищает неидемпотентный POST от дублей при ретрае (модуль 25).

---
<!-- Ваше решение ниже -->
