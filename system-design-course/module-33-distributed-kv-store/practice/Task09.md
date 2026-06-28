# Задача 09 — Модуль 33: Distributed KV-store

## ЗАДАНИЕ
Найдите узкие места KV-хранилища и спроектируйте вариант распределённого кэша на том же скелете.

## ВХОДНЫЕ ДАННЫЕ
Эксплуатация выявила: один «горячий» ключ перегружает свой узел; при чтении с нескольких реплик растёт tail latency (p99); при добавлении узлов идёт rebalancing. Отдельно нужен распределённый кэш (in-memory ускоритель перед БД).

## ТРЕБОВАНИЯ
- Для каждого узкого места (hot key, tail latency, rebalancing) дайте решение со ссылкой на модуль.
- Спроектируйте distributed cache на том же скелете: что переиспользуется, что меняется.
- Объясните разницу distributed cache и persistent KV-store.
- Укажите роль eviction в кэше.

## ФОРМАТ РЕЗУЛЬТАТА
Узкие места (таблица) → distributed cache (переиспользование/отличия) → cache vs persistent KV → eviction.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Hot key → кэш перед стором / репликация хот-ключа / разбиение ключа (модуль 26).
- [ ] Tail latency → hedged requests (дубль на 2-ю реплику), таймауты (модуль 26).
- [ ] Rebalancing → vnodes минимизируют перемещение (модуль 14).
- [ ] Distributed cache: переиспует consistent hashing для шардирования; меняется — in-memory вместо durable, eviction вместо persistence.
- [ ] Cache vs persistent KV: кэш — in-memory + eviction (цель latency, данные эфемерны), стор — durable (источник правды).
- [ ] Eviction (LRU/LFU/TTL, модуль 9) удерживает горячие данные в ограниченной памяти.

## ПОДСКАЗКА
Hot key → кэш/репликация/разбиение; tail latency → hedged requests (модуль 26); rebalancing → vnodes (модуль 14). Distributed cache = тот же consistent hashing, но in-memory + eviction (LRU/LFU/TTL, модуль 9) вместо durability. Кэш — ускоритель (эфемерный), стор — источник правды (durable).

---
<!-- Ваше решение ниже -->
