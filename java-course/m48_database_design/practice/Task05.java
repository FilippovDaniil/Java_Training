package m48_database_design.practice;

/**
 * Задача 05 — Модуль 48: Приведение к 3НФ
 *
 * ДАНО (во 2НФ, но не в 3НФ):
 *   employees(id, name, dept_id, dept_name, dept_location)
 *   Проблема: dept_name и dept_location зависят от dept_id
 *   (неключевого атрибута) → транзитивная зависимость.
 *
 * ЗАДАНИЕ:
 *   1) объясните в комментарии транзитивную зависимость;
 *   2) приведите к 3НФ: вынесите атрибуты отдела в таблицу
 *      departments(dept_id, dept_name, dept_location);
 *   3) напишите CREATE TABLE для результата (employees + departments).
 *
 * ПОДСКАЗКА:
 *   После нормализации employees хранит только dept_id (FK),
 *   а название/локация отдела — в departments.
 */
public class Task05 {
    public static void main(String[] args) {
        String sql = """
            -- Приведите employees к 3НФ (устраните транзитивную зависимость)
            """;
        System.out.println(sql);
    }
}
