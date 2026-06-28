# Задача 10 — Модуль 38 (мини-проект): Полный HLD файлового хранилища

## ЗАДАНИЕ
Соберите полный high-level design облачного файлового хранилища по фреймворку design-интервью. Синтез модуля.

## ВХОДНЫЕ ДАННЫЕ
Условие интервью: «Спроектируйте Dropbox / Google Drive: загрузка, синхронизация между устройствами, шеринг, версии; десятки млн пользователей, петабайты данных». 45–60 минут.

## ТРЕБОВАНИЯ
Дизайн-документ по шагам:
1. **Требования** — FR/NFR, главная ось (storage) и главное NFR (durability).
2. **Estimation** — storage (петабайты), эффект дедупа/compression, профиль нагрузки.
3. **Metadata vs content** — БД для дерева/версий/прав + object storage для блоков.
4. **Chunking** — блоки, дельта-sync, дедуп, параллельная/resumable загрузка.
5. **Content-addressable + дедуп** — хэш = ключ блока, протокол заливки.
6. **Sync** — notification service + дельта только изменённых чанков.
7. **Конфликты/версии/шеринг + узкие места** — conflict copy (не LWW), ACL; metadata/sync/hot files/storage cost.

## ФОРМАТ РЕЗУЛЬТАТА
HLD из 7 разделов + диаграмма (клиент/watcher → metadata + block service → object storage + notification) + расчёты storage.

## КРИТЕРИИ ПРИЁМКИ
- [ ] Требования: главная ось storage, главное NFR durability; scope зафиксирован.
- [ ] Estimation: петабайты, показан эффект дедупа/compression.
- [ ] Разделение metadata (БД) vs content (object storage); файл = список хэшей блоков.
- [ ] Chunking: дельта-sync, дедуп, параллельная/resumable загрузка.
- [ ] Content-addressable storage (хэш = ключ) + дедупликация + протокол заливки по хэшам.
- [ ] Sync: notification service + дельта только изменённых блоков (не весь файл).
- [ ] Конфликты через conflict copy (не LWW); ACL/шеринг; узкие места (metadata/sync/hot files/storage); связи с модулями 10/12/36/13/9/7.

## ПОДСКАЗКА
По фреймворку: требования (storage/durability) → estimation (дедуп) → metadata vs content → chunking → content-addressable+дедуп → sync (notification+дельта) → конфликты/шеринг+узкие места. Стержень: два слоя (метаданные/содержимое), chunking даёт дельта-sync+дедуп+resumable, durability — главное.

---
<!-- Ваше решение ниже -->
