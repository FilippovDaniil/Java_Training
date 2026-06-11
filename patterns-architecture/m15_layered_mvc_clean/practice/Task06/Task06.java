package m15_layered_mvc_clean.practice.task06;

/**
 * Задача 06 — Тема 15: слои на пути ЗАПИСИ (бизнес-правило в сервисе)
 *
 * ЗАДАНИЕ:
 *   Покажите, зачем нужен средний слой: на пути записи бизнес-правило живёт в
 *   сервисе, а не в контроллере и не в репозитории.
 *     - Persistence: Stock (файл Stock.java) — id, qty; addQty(int), getQty();
 *       StockRepository (файл ...) interface findById/save; InMemoryStockRepository;
 *     - Business: StockService (файл StockService.java): addStock(id, qty) —
 *       если qty <= 0 → бросить IllegalArgumentException; иначе найти, увеличить,
 *       сохранить; quantity(id);
 *     - Presentation: StockController (файл ...) — addStock(id, qty) делегирует сервису.
 *   В main: добавьте остаток валидно, попробуйте добавить отрицательный (исключение),
 *   выведите итоговое количество.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   После +10: 10
 *   Ошибка: количество должно быть > 0
 *   Итог: 10
 *
 * ТРЕБОВАНИЯ:
 *   - проверка qty > 0 — в сервисе (бизнес-слой), не в контроллере и не в репозитории;
 *   - контроллер только делегирует, репозиторий только хранит;
 *   - путь записи: Controller → Service → Repository.
 *
 * ПОДСКАЗКА:
 *   Средний слой оправдан, когда добавляет ценность (валидацию, правила). Если бы
 *   сервис лишь 1-в-1 звал репозиторий — он был бы лишней обёрткой.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: соберите controller→service→repository; addStock валидно и с qty<=0 (поймать),
        //       выведите quantity(id)
    }
}
