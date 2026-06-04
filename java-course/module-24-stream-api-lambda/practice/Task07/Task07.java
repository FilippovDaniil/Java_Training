/**
 * Задача 07 — Модуль 24 (МИНИ-ПРОЕКТ): Аналитика по сотрудникам
 *
 * ЗАДАНИЕ:
 *   Дан список сотрудников (имя, отдел, зарплата, возраст). Используя
 *   ТОЛЬКО Stream API (без обычных циклов), посчитайте:
 *     1) имена всех сотрудников из отдела "IT", отсортированные по алфавиту;
 *     2) среднюю зарплату по компании;
 *     3) сотрудника с максимальной зарплатой;
 *     4) количество сотрудников старше 30;
 *     5) группировку: отдел -> список имён сотрудников (groupingBy);
 *     6) суммарный фонд зарплат по каждому отделу
 *        (groupingBy + summingDouble).
 *
 * ПОДСКАЗКИ:
 *   - фильтр по отделу: .filter(e -> e.dept.equals("IT")).map(e -> e.name);
 *   - средняя: .mapToDouble(e -> e.salary).average().orElse(0);
 *   - максимум: .max(Comparator.comparingDouble(e -> e.salary));
 *   - группировка: Collectors.groupingBy(e -> e.dept,
 *                    Collectors.mapping(e -> e.name, Collectors.toList()));
 *   - фонд: Collectors.groupingBy(e -> e.dept,
 *                    Collectors.summingDouble(e -> e.salary)).
 */

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task07 {
    public static void main(String[] args) {
        List<Emp> staff = List.of(
            new Emp("Иван", "IT", 80000, 28),
            new Emp("Мария", "HR", 60000, 35),
            new Emp("Пётр", "IT", 95000, 41),
            new Emp("Анна", "Sales", 70000, 30),
            new Emp("Олег", "IT", 75000, 33),
            new Emp("Елена", "HR", 65000, 26)
        );
        // Ваш код здесь (используйте Stream API)
    }
}
