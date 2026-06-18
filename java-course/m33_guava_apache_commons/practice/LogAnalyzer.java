package m33_guava_apache_commons.practice;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class LogAnalyzer {
    private final Multiset<String> levelCounts = HashMultiset.create();
    private final Multimap<String, String> moduleMessages = ArrayListMultimap.create();
    private int processedCount = 0;
    private int skippedCount = 0;
    private final java.util.List<String> errorMessages = new java.util.ArrayList<>();

    /**
     * Анализирует массив логов
     */
    public void analyzeLogs(String[] logs) {
        System.out.println("Обработка логов...");
        System.out.println("Всего записей: " + (logs != null ? logs.length : 0));
        System.out.println();

        if (logs == null) {
            System.out.println("❌ Массив логов null");
            return;
        }

        // Создаем Splitter для парсинга
        Splitter splitter = Splitter.on(';')
                .trimResults()          // Удаляем пробелы вокруг частей
                .omitEmptyStrings();    // Пропускаем пустые части

        for (String line : logs) {
            // 1. Пропускаем пустые строки с помощью StringUtils
            if (StringUtils.isBlank(line)) {
                skippedCount++;
                System.out.println("   ⏭️ Пропущена пустая строка");
                continue;
            }

            System.out.println("   📝 Обработка: " + line);

            try {
                // 2. Парсим строку с помощью Splitter
                java.util.List<String> parts = splitter.splitToList(line);

                // Проверяем, что у нас 3 части
                if (parts.size() < 3) {
                    skippedCount++;
                    System.out.println("      ⚠️ Некорректный формат: ожидается 3 части, получено " + parts.size());
                    continue;
                }

                // 3. Извлекаем части
                String level = StringUtils.upperCase(parts.get(0)); // Приводим к верхнему регистру
                String module = parts.get(1);
                String message = parts.get(2);

                // 4. Проверяем части на пустоту
                if (StringUtils.isBlank(level) || StringUtils.isBlank(module)) {
                    skippedCount++;
                    System.out.println("      ⚠️ Уровень или модуль пустые: level='" + level + "', module='" + module + "'");
                    continue;
                }

                // 5. Добавляем в Multiset для подсчета уровней
                levelCounts.add(level);

                // 6. Добавляем в Multimap для группировки по модулям
                // Если сообщение пустое, используем значение по умолчанию
                String formattedMessage = StringUtils.defaultIfBlank(message, "<пустое сообщение>");
                moduleMessages.put(module, formattedMessage);

                processedCount++;
                System.out.println("      ✅ Добавлено: level=" + level + ", module=" + module + ", message=" + formattedMessage);

            } catch (Exception e) {
                skippedCount++;
                errorMessages.add("Ошибка при обработке: " + line + " - " + e.getMessage());
                System.out.println("      ❌ Ошибка: " + e.getMessage());
            }
        }

        System.out.println("\nОбработка завершена.");
        System.out.println("   ✅ Обработано: " + processedCount);
        System.out.println("   ⏭️  Пропущено: " + skippedCount);
        if (!errorMessages.isEmpty()) {
            System.out.println("   ❌ Ошибок: " + errorMessages.size());
        }
    }

    /**
     * Выводит отчет по уровням и модулям
     */
    public void printReport() {
        System.out.println("\n" + "=" .repeat(60));
        System.out.println("\n📊 ОТЧЕТ ПО ЛОГАМ");
        System.out.println("=" .repeat(60));

        // 1. Отчет по уровням
        printLevelReport();

        // 2. Отчет по модулям
        printModuleReport();
    }

    /**
     * Выводит отчет по уровням
     */
    private void printLevelReport() {
        System.out.println("\n=== УРОВНИ ===");

        if (levelCounts.isEmpty()) {
            System.out.println("   Нет данных по уровням");
            return;
        }

        // Сортируем уровни для красивого вывода
        java.util.Set<String> levels = levelCounts.elementSet();
        java.util.List<String> sortedLevels = new java.util.ArrayList<>(levels);
        java.util.Collections.sort(sortedLevels);

        int total = 0;
        for (String level : sortedLevels) {
            int count = levelCounts.count(level);
            total += count;
            // Используем StringUtils для форматирования
            String paddedLevel = StringUtils.rightPad(level, 10);
            System.out.println("   " + paddedLevel + ": " + count);
        }
        System.out.println("   " + "-" .repeat(15));
        System.out.println("   " + StringUtils.rightPad("ИТОГО", 10) + ": " + total);
    }

    /**
     * Выводит отчет по модулям
     */
    private void printModuleReport() {
        System.out.println("\n=== ПО МОДУЛЯМ ===");

        if (moduleMessages.isEmpty()) {
            System.out.println("   Нет данных по модулям");
            return;
        }

        // Сортируем модули
        java.util.Set<String> modules = moduleMessages.keySet();
        java.util.List<String> sortedModules = new java.util.ArrayList<>(modules);
        java.util.Collections.sort(sortedModules);

        // Создаем Joiner для объединения сообщений
        Joiner joiner = Joiner.on("; ").skipNulls();

        for (String module : sortedModules) {
            Collection<String> messages = moduleMessages.get(module);

            // Форматируем модуль
            String formattedModule = StringUtils.rightPad(module, 15);

            // Объединяем сообщения с помощью Joiner
            String joinedMessages = joiner.join(messages);

            // Если сообщений много, обрезаем для читаемости
            if (joinedMessages.length() > 80) {
                joinedMessages = StringUtils.abbreviate(joinedMessages, 80);
            }

            System.out.println("   " + formattedModule + ": " + joinedMessages);
            System.out.println("   " + " ".repeat(17) + "(сообщений: " + messages.size() + ")");
        }
    }

    /**
     * Выводит дополнительную статистику
     */
    public void printAdditionalStats() {
        System.out.println("\n📈 ДОПОЛНИТЕЛЬНАЯ СТАТИСТИКА");

        // 1. Общая статистика
        System.out.println("\n   Общая статистика:");
        System.out.println("   - Всего обработано записей: " + processedCount);
        System.out.println("   - Всего пропущено записей: " + skippedCount);

        // 2. Статистика по уровням
        System.out.println("\n   Статистика по уровням:");
        for (String level : levelCounts.elementSet()) {
            int count = levelCounts.count(level);
            double percentage = processedCount > 0 ? (double) count / processedCount * 100 : 0;
            System.out.printf("   - %s: %d (%.1f%%)\n", level, count, percentage);
        }

        // 3. Статистика по модулям
        System.out.println("\n   Статистика по модулям:");
        for (String module : moduleMessages.keySet()) {
            int count = moduleMessages.get(module).size();
            double percentage = processedCount > 0 ? (double) count / processedCount * 100 : 0;
            System.out.printf("   - %s: %d (%.1f%%)\n", module, count, percentage);
        }

        // 4. Ошибки
        if (!errorMessages.isEmpty()) {
            System.out.println("\n   ❌ Ошибки обработки:");
            for (String error : errorMessages) {
                System.out.println("   - " + error);
            }
        }

        // 5. Использование StringUtils
        System.out.println("\n   Примеры использования StringUtils:");
        String testText = "  Some TEXT with   spaces  ";
        System.out.println("   - trim: '" + StringUtils.trim(testText) + "'");
        System.out.println("   - normalizeSpace: '" + StringUtils.normalizeSpace(testText) + "'");
        System.out.println("   - swapCase: '" + StringUtils.swapCase(testText) + "'");

        // 6. Использование Joiner
        System.out.println("\n   Пример использования Joiner:");
        java.util.List<String> exampleList = java.util.Arrays.asList("один", "два", "три");
        String joined = Joiner.on(", ").join(exampleList);
        System.out.println("   - Join: '" + joined + "'");
    }
}
