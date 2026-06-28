# Задача 10 — Модуль 35 (мини-проект): Полный HLD мессенджера

## ЗАДАНИЕ
Соберите полный high-level design мессенджера по фреймворку design-интервью. Синтез модуля.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте мессенджер (WhatsApp): 1:1 и групповые чаты, real-time доставка, статусы, presence, offline-доставка, ~200M DAU». 45–60 минут.

## ТРЕБОВАНИЯ
Дизайн-документ по шагам:
1. **Требования** — FR/NFR, scope (real-time, порядок, durability).
2. **Estimation** — сообщения/с, одновременные соединения, storage.
3. **Соединение** — WebSocket vs polling, connection servers.
4. **Маршрутизация** — session registry, доставка к серверу получателя.
5. **Доставка** — online/offline, at-least-once+дедуп, статусы; порядок per-conversation.
6. **Хранение** — wide-column, partition по conversation_id; медиа в object storage.
7. **Presence + группы + узкие места** — heartbeat/fan-out, группы, масштаб соединений.

## ФОРМАТ РЕЗУЛЬТАТА
HLD из 7 разделов + диаграмма (client↔WS gateway↔message service→store+inbox; session registry; presence; notification) + расчёты.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования: real-time, порядок, durability, масштаб; scope зафиксирован.
- [ ] Estimation: ~100k сообщений/с, десятки–сотни млн соединений, петабайтная история.
- [ ] WebSocket + connection servers (server push, удержание соединений).
- [ ] Session registry + маршрутизация к серверу получателя.
- [ ] Доставка online/offline, at-least-once+дедуп, статусы; порядок per-conversation (seq).
- [ ] Хранение: wide-column, partition по conversation_id; медиа — object storage + CDN.
- [ ] Presence (heartbeat, ленивый fan-out), группы (как celebrity для больших), узкие места (соединения/presence/hot groups); связи с модулями 8/11/13/15/23/34/36.

## ПОДСКАЗКА
По фреймворку: требования→estimation→WebSocket+connection servers→session registry/маршрутизация→доставка online/offline (at-least-once+дедуп+receipts)+порядок per-conversation→wide-column хранилище→presence+группы+узкие места. Стержень — real-time с persistent-соединениями и маршрутизация к получателю. Дальше — Модуль 36: уведомления (push для offline).

---
<!-- Ваше решение ниже -->
