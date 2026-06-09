/**
 * Задача 02 — Тема 26: Feature Toggle (переключатель реализаций)
 *
 * ЗАДАНИЕ:
 *   Флаг выбирает активную реализацию — для постепенного включения нового и
 *   быстрого отката:
 *     - Service (файл Service.java): String run();
 *     - LegacyService ("старая логика") и ModernService ("новая логика");
 *     - ToggledService (файл ToggledService.java): хранит legacy, modern и флаг
 *       useModern; run() возвращает modern.run() при useModern, иначе legacy.run();
 *       setUseModern(boolean) переключает флаг.
 *   В main: сначала с флагом OFF (старая логика), затем включите флаг и вызовите
 *   снова (новая логика).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   старая логика
 *   новая логика
 *
 * ТРЕБОВАНИЯ:
 *   - выбор реализации — по флагу в рантайме (можно переключить и откатить);
 *   - обе реализации за общим интерфейсом Service;
 *   - ПОМНИТЕ: после миграции toggle и старую ветку удаляют (иначе Lava Flow, тема 22).
 *
 * ПОДСКАЗКА:
 *   run() = useModern ? modern.run() : legacy.run(); Toggle даёт безопасное
 *   постепенное включение и мгновенный откат при проблемах.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: ToggledService(legacy, modern, false) → run(); setUseModern(true) → run()
    }
}
