# Задача 04 — Модуль 47: Симуляция ответа с таймингом

## ЗАДАНИЕ
Проведите симуляцию ответа на классическую задачу с разбивкой по времени и think-aloud.

## ВХОДНЫЕ ДАННЫЕ
Задача: «спроектируйте мессенджер (chat)». 60 минут. Нужно показать ход ответа senior-уровня по фазам.

## ТРЕБОВАНИЯ
- Распишите ход ответа по фазам с таймингом (как worked example в теории).
- Покажите think-aloud (что проговариваете) и уточняющие вопросы на старте.
- На deep dive выберите критичный компонент (доставка online/offline или connection servers).
- Проговорите хотя бы 2 trade-offs вслух (WebSocket vs polling; порядок per-conversation).

## ФОРМАТ РЕЗУЛЬТАТА
Фазы с таймингом (0–5, 5–10, ...) → think-aloud → deep dive → trade-offs вслух.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Ход ответа разбит по фазам с таймингом (требования→...→wrap-up в пределах 60 мин).
- [ ] Показаны think-aloud и уточняющие вопросы на старте.
- [ ] Deep dive в критичный компонент (доставка online/offline или connection servers — модуль 35).
- [ ] Проговорены ≥2 trade-offs вслух (WebSocket vs polling; порядок/at-least-once+дедуп).

## ПОДСКАЗКА
Фазы с таймингом как в теории. Chat (модуль 35): архетип real-time. Deep dive — доставка online/offline или connection servers. Trade-offs: WebSocket vs polling, порядок per-conversation, at-least-once+дедуп.

---
<!-- Ваше решение ниже -->
