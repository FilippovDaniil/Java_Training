# Задача 04 — Модуль 49: Vector DB и semantic search

## ЗАДАНИЕ
Сравните semantic search (vector DB) с keyword search и спроектируйте поиск по векторам на масштабе.

## ВХОДНЫЕ ДАННЫЕ
Нужно искать по смыслу (синонимы, перефразировки), а не по точному совпадению слов, среди миллиардов векторов.

## ТРЕБОВАНИЯ
- Сравните keyword search (inverted index, модуль 17) и semantic search (embeddings, близость смысла).
- Опишите vector DB: хранение embeddings + поиск по косинусной близости.
- Объясните, почему точный k-NN на масштабе дорог → ANN (approximate nearest neighbor, HNSW).
- Покажите trade-off ANN (чуть меньше точности ради скорости) — как «приближённость vs точность» в других модулях.

## ФОРМАТ РЕЗУЛЬТАТА
Keyword vs semantic → vector DB (косинусная близость) → ANN/HNSW → trade-off приближённости.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Keyword (inverted index, точное совпадение, модуль 17) vs semantic (embeddings, близость смысла).
- [ ] Vector DB: хранит embeddings, ищет по косинусному расстоянию.
- [ ] Точный k-NN на млрд векторов дорог → ANN (HNSW) — приближённый, быстрый.
- [ ] Trade-off: ANN жертвует капелькой точности ради скорости (как приближённость в leaderboard/метриках).

## ПОДСКАЗКА
Keyword (inverted index, термы, 17) vs semantic (embeddings, смысл). Vector DB: косинусная близость. Точный k-NN дорог → ANN/HNSW (приближённый, быстрый). Trade-off точность vs скорость.

---
<!-- Ваше решение ниже -->
