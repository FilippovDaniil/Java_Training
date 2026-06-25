# Задача 07 — Модуль 30: Contract-first / OpenAPI

## ЗАДАНИЕ
Опишите пагинацию, фильтры и сортировку коллекции в OpenAPI.

## ВХОДНЫЕ ДАННЫЕ
**FoodExpress**, `GET /orders` (из модуля 29): фильтры (status, restaurantId, minTotal, dateFrom/dateTo), сортировка (`sort`), пагинация. Нужно формализовать в OpenAPI с переиспользуемыми параметрами.

## ТРЕБОВАНИЯ
- Опишите query-параметры в OpenAPI (`in: query`): фильтры, sort, пагинация (limit + offset или cursor).
- Вынесите типовые параметры (limit, offset/cursor) в `components/parameters`.
- Опишите схему ответа коллекции (массив + метаданные пагинации: total/nextCursor).
- Укажите типы, дефолты, ограничения параметров (например, limit max).
- Спецификация валидна.

## ФОРМАТ РЕЗУЛЬТАТА
OpenAPI YAML: параметры запроса (фильтры/sort/пагинация) + схема ответа коллекции + переиспользуемые parameters.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Query-параметры описаны (`in: query`) с типами и дефолтами.
- [ ] Фильтры (status enum, restaurantId, minTotal, dateFrom/dateTo) и `sort`.
- [ ] Пагинация (limit + offset или cursor) с ограничением limit (maximum).
- [ ] Типовые параметры вынесены в `components/parameters` и подключены через `$ref`.
- [ ] Схема ответа коллекции (массив Order + метаданные пагинации).
- [ ] YAML валиден.

## ПОДСКАЗКА
Query-параметры: `parameters: - name: status, in: query, schema: {type: string, enum: [...]}`. Пагинацию (`limit`, `offset`/`cursor`) вынести в `components/parameters` и `$ref`. Ограничить `limit` через `maximum`. Ответ коллекции: объект с `data: [Order]` + `meta: {total/nextCursor}` (или массив + заголовки). Дефолты — `default:`. Проверить в Swagger Editor.

---
<!-- Ваше решение ниже -->
