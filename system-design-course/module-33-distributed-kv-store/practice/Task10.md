# Задача 10 — Модуль 33 (мини-проект): Полный дизайн distributed KV-store

## ЗАДАНИЕ
Спроектируйте распределённое key-value хранилище в стиле Dynamo/Cassandra от требований до членства кластера. Синтез модуля.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте распределённое KV-хранилище: огромный масштаб, высокая доступность (always-writeable), настраиваемая согласованность, низкая latency, без мастера». 45–60 минут.

## ТРЕБОВАНИЯ
Дизайн по шагам:
1. **Требования** — FR (get/put/delete), NFR (масштаб, HA, latency, настраиваемая согласованность); почему KV.
2. **Распределение данных** — consistent hashing + vnodes.
3. **Репликация** — N, preference list, sync/async.
4. **Согласованность** — кворум W+R>N, настройка под данные (AP/PACELC).
5. **Конфликты** — vector clocks/LWW, read-repair, merge/CRDT.
6. **Членство и отказы** — gossip, hinted handoff, anti-entropy (Merkle).
7. **Storage + путь запроса + узкие места** — LSM, координатор, hot keys/tail latency.

## ФОРМАТ РЕЗУЛЬТАТА
Дизайн из 7 разделов + диаграмма (кольцо с vnodes, preference list, координатор, потоки put/get).

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования FR/NFR + обоснование KV (масштаб без границ за счёт простоты модели).
- [ ] Распределение: consistent hashing + vnodes (равномерность/мощность/rebalancing).
- [ ] Репликация: N, preference list, sync/async с обоснованием.
- [ ] Кворум W+R>N с примером настройки (W=2,R=2,N=3) и связью с AP/PACELC.
- [ ] Разрешение конфликтов (vector clocks vs LWW, read-repair, CRDT/merge).
- [ ] Членство/отказы: gossip + hinted handoff + anti-entropy (Merkle), без мастера.
- [ ] Storage (LSM), путь запроса (координатор = любой узел), узкие места (hot keys/tail latency); связи с модулями 12/14/21/22/26.

## ПОДСКАЗКА
Синтез распределёнки: KV-модель/NFR (HA+настраиваемая согласованность) → consistent hashing+vnodes → N-репликация (preference list) → кворум W+R>N (AP/PACELC) → конфликты (vector clocks/LWW + read-repair) → членство (gossip+hinted handoff+anti-entropy/Merkle) → LSM + координатор + узкие места. Дальше — Модуль 34: newsfeed (fan-out push vs pull, celebrity problem).

---
<!-- Ваше решение ниже -->
