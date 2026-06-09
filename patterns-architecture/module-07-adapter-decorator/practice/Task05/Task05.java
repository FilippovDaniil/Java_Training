/**
 * Задача 05 — Тема 07: Decorator — порядок обёрток важен
 *
 * ЗАДАНИЕ:
 *   Покажите, что ПОРЯДОК декораторов влияет на результат (как сжатие и
 *   шифрование данных):
 *     - интерфейс DataChannel (файл DataChannel.java): String pack(String data);
 *     - RawChannel (файл RawChannel.java): возвращает данные как есть;
 *     - абстрактный ChannelDecorator (файл ChannelDecorator.java) хранит inner;
 *     - CompressDecorator оборачивает результат inner в "C(...)";
 *       EncryptDecorator — в "E(...)".
 *   В main соберите ДВЕ цепочки над RawChannel: compress→encrypt и
 *   encrypt→compress, прогоните одну строку и сравните результаты — они разные.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   compress, затем encrypt: E(C(data))
 *   encrypt, затем compress: C(E(data))
 *
 * ТРЕБОВАНИЯ:
 *   - оба декоратора реализуют DataChannel и вкладываются;
 *   - результат двух разных порядков обёрток ОТЛИЧАЕТСЯ;
 *   - каждый декоратор сначала зовёт inner.pack(...), затем оборачивает.
 *
 * ПОДСКАЗКА:
 *   new EncryptDecorator(new CompressDecorator(raw)) → сначала сожмёт (внутренний),
 *   потом зашифрует (внешний) → E(C(data)). Поменяйте местами — получите C(E(data)).
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: соберите обе цепочки над RawChannel, прогоните "data", сравните вывод
    }
}
