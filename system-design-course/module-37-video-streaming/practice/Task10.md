# Задача 10 — Модуль 37 (мини-проект): Полный HLD видеостриминга

## ЗАДАНИЕ
Соберите полный high-level design видеосервиса по фреймворку design-интервью. Синтез модуля и завершение под-батча.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте видеосервис (YouTube/Netflix): загрузка и воспроизведение видео, глобальная аудитория, разные устройства/сети, миллиарды просмотров». 45–60 минут. Уточните, ближе к UGC (YouTube) или каталогу (Netflix).

## ТРЕБОВАНИЯ
Дизайн-документ по шагам:
1. **Требования** — FR/NFR, главные оси (storage/egress).
2. **Estimation** — storage (петабайты), egress (доминанта стоимости).
3. **Два пути** — upload/ingestion vs streaming/playback.
4. **Транскодинг** — async-конвейер, варианты, чанки.
5. **ABR + протокол** — adaptive bitrate, HLS/DASH поверх HTTP.
6. **Хранение + CDN** — object storage, tiering, CDN как ядро (egress), pre-positioning.
7. **Метаданные/просмотры + узкие места** — async-просмотры, egress/storage/transcoding/viral; Netflix vs YouTube.

## ФОРМАТ РЕЗУЛЬТАТА
HLD из 7 разделов + диаграмма (upload pipeline + playback через CDN) + расчёты storage/egress.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования: главные оси storage/egress; scope (UGC vs каталог) зафиксирован.
- [ ] Estimation: петабайтное хранилище, egress как доминирующая статья cost.
- [ ] Два пути разделены (upload+async-транскодинг vs playback из CDN).
- [ ] Транскодинг: async-конвейер через очередь, несколько вариантов, чанки.
- [ ] ABR + HLS/DASH поверх HTTP (CDN-friendly).
- [ ] Object storage + tiering; CDN как ядро доставки (latency + поглощение egress) + pre-positioning популярного.
- [ ] Async-подсчёт просмотров; узкие места (egress/storage/transcoding/viral); отмечена разница Netflix vs YouTube; связи с модулями 7/10/15/26.

## ПОДСКАЗКА
По фреймворку: требования (оси storage/egress)→estimation→два пути (upload+транскодинг vs playback из CDN)→транскодинг (async, варианты, чанки)→ABR+HLS/DASH→object storage+tiering+CDN (ядро, egress)→async-просмотры+узкие места. Стержень — система про байты: egress доминирует → CDN ядро; транскодинг async. Этим завершается под-батч b Части 8.

---
<!-- Ваше решение ниже -->
