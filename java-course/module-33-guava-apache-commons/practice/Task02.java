/**
 * Задача 02 — Модуль 33: Guava BiMap
 *
 * ТРЕБУЕТСЯ ЗАВИСИМОСТЬ: com.google.guava:guava:33.2.1-jre
 *
 * ЗАДАНИЕ:
 *   Постройте двунаправленный словарь "код страны <-> название"
 *   через HashBiMap. Реализуйте поиск в обе стороны:
 *     - по коду "RU" получить "Россия";
 *     - по названию "Россия" получить код "RU" (через inverse()).
 *   Попробуйте добавить дубликат значения и понаблюдайте поведение.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   RU -> Россия
 *   Россия -> RU
 *
 * ПОДСКАЗКА:
 *   BiMap<String,String> b = HashBiMap.create();
 *   b.put("RU","Россия"); b.inverse().get("Россия");
 *   В BiMap значения тоже уникальны — повторное значение вызовет ошибку
 *   (или используйте forcePut).
 */
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class Task02 {
    public static void main(String[] args) {
        // Создайте BiMap кодов стран и ищите в обе стороны
    }
}
