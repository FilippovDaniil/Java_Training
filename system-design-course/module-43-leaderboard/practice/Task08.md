# Задача 08 — Модуль 43: Персистентность

## ЗАДАНИЕ
Спроектируйте персистентность лидерборда поверх in-memory Redis.

## ВХОДНЫЕ ДАННЫЕ
Лидерборд живёт в Redis (in-memory) ради скорости. При сбое узла данные в памяти теряются, но лидерборд (особенно all-time) терять нельзя.

## ТРЕБОВАНИЯ
- Объясните проблему: Redis in-memory → сбой = потеря ZSET.
- Предложите durable backing: источник правды счетов в durable store (события/БД).
- Опишите варианты: Redis-persistence (RDB/AOF) и/или восстановление ZSET из durable store.
- Покажите, как восстановить лидерборд после сбоя.

## ФОРМАТ РЕЗУЛЬТАТА
Проблема in-memory → durable backing (источник правды) → Redis-persistence/восстановление → процедура recovery.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Проблема: Redis in-memory → сбой = потеря лидерборда.
- [ ] Durable backing: источник правды счетов в durable store (события/БД).
- [ ] Варианты: Redis-persistence (RDB/AOF) и/или пересборка ZSET из durable store.
- [ ] Описано восстановление ZSET после сбоя (из персистентности или backing store).

## ПОДСКАЗКА
Redis in-memory → сбой = потеря. Durable backing: счета в durable store (источник правды). Redis-persistence (RDB/AOF) или пересборка ZSET из store. Recovery: восстановить ZSET.

---
<!-- Ваше решение ниже -->
