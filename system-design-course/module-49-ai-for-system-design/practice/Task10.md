# Задача 10 — Модуль 49 (мини-проект): ИИ-фича end-to-end

## ЗАДАНИЕ
Спроектируйте ИИ-фичу для системы end-to-end по фреймворку. Финальная задача курса.

## ВХОДНЫЕ ДАННЫЕ
Выберите ИИ-фичу, например: RAG-ассистент по базе знаний / семантический поиск по каталогу / рекомендации / суммаризация. Спроектируйте её как полноценную распределённую систему.

## ТРЕБОВАНИЯ
Дизайн по фреймворку:
1. **Требования** — FR/NFR (включая latency inference, качество, cost).
2. **Estimation** — нагрузка inference, GPU/token cost (новая ось).
3. **Архитектура** — inference serving / RAG-конвейер (embeddings → vector DB → LLM) / feature store, по необходимости.
4. **LLM-специфика** — streaming, context window, guardrails, fallback.
5. **Узкие места** — inference latency, GPU cost, масштаб vector index, eval.
6. **Связь с фундаментом** — где переиспользуются кэш/очереди/надёжность/cost из курса.

## ФОРМАТ РЕЗУЛЬТАТА
Дизайн из 6 разделов + диаграмма (конвейер ИИ-фичи) + оценка GPU/token cost.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования FR/NFR (latency inference, качество, cost); scope зафиксирован.
- [ ] Estimation: нагрузка inference + GPU/token cost как ось (модуль 7).
- [ ] Архитектура: inference serving и/или RAG (embeddings→vector DB→LLM), feature store при необходимости.
- [ ] LLM-специфика: streaming, context window, guardrails, fallback/circuit breaker (модуль 23).
- [ ] Узкие места: inference latency (26), GPU cost (7), vector index (13), eval (27).
- [ ] Явно показано переиспользование фундамента (кэш/очереди/надёжность/cost) — ИИ дополняет, не заменяет.

## ПОДСКАЗКА
Выбери ИИ-фичу → требования (latency/качество/cost) → estimation (GPU/token cost) → архитектура (inference serving / RAG: embeddings→vector DB→LLM / feature store) → LLM-специфика (streaming/guardrails/fallback) → узкие места (26/7/13/27) → связь с фундаментом. Финал курса.

---
<!-- Ваше решение ниже -->
