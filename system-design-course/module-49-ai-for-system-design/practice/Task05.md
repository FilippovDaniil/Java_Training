# Задача 05 — Модуль 49: Feature store и training-serving skew

## ЗАДАНИЕ
Спроектируйте feature store и объясните проблему training-serving skew.

## ВХОДНЫЕ ДАННЫЕ
ML-модели нужны фичи и при обучении (батч, история), и при inference (real-time, низкая latency). Если фичи считаются по-разному, модель деградирует.

## ТРЕБОВАНИЯ
- Опишите offline store (фичи для обучения: батч, история) и online store (для inference: низкая latency).
- Объясните training-serving skew: расхождение фич обучение↔прод → деградация модели.
- Покажите решение: единый источник фич для обучения и inference (feature store).
- Свяжите с разделением OLTP/OLAP (18) и конвейерами данных (20).

## ФОРМАТ РЕЗУЛЬТАТА
Offline + online store → training-serving skew → единый источник → связь с 18/20.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Offline store (обучение: батч, история) + online store (inference: низкая latency).
- [ ] Training-serving skew: фича в обучении ≠ в проде → незаметная деградация модели.
- [ ] Решение: feature store как единый источник фич для обоих путей.
- [ ] Связь с OLTP/OLAP (18) и конвейерами данных (20).

## ПОДСКАЗКА
Offline (обучение, батч) + online (inference, latency). Training-serving skew: фичи расходятся → деградация. Решение: единый источник (feature store). Связь 18 (online/offline) и 20 (пайплайны).

---
<!-- Ваше решение ниже -->
