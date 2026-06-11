package m92_hibernate_deep_dive_diagnostics.practice;

/**
 * Задача 01 — Модуль 92: конфигурация диагностики SQL (носитель конфигурации)
 *
 * ФОРМАТ: конфиг-носитель. Обычный .java с text-блоком и println (компилируется bare-javac,
 *   JDK 15+). Сами настройки вы поместите в persistence.xml и logback.xml — см. ниже.
 *
 * ЗАДАНИЕ:
 *   1) В persistence.xml включите видимость SQL:
 *        hibernate.show_sql=true
 *        hibernate.format_sql=true
 *        hibernate.use_sql_comments=true
 *        hibernate.generate_statistics=true
 *   2) В logback.xml добавьте логгеры, чтобы видеть SQL И значения параметров:
 *        <logger name="org.hibernate.SQL" level="DEBUG"/>
 *        <logger name="org.hibernate.orm.jdbc.bind" level="TRACE"/>
 *   3) Впишите оба фрагмента в SQL/CONFIG-блоки ниже (задача напечатает их как образец).
 *   4) В комментарии отметьте: TRACE-логи параметров — ТОЛЬКО для dev (шум + утечка данных).
 *
 * ЦЕЛЬ: научиться «видеть» реальный SQL и связанные параметры — основа любой диагностики.
 *
 * ПОДСКАЗКА: show_sql печатает SQL с '?'; значения параметров даёт ТОЛЬКО логгер
 *            org.hibernate.orm.jdbc.bind на уровне TRACE.
 */
public class Task01 {
    public static void main(String[] args) {
        String persistenceXml = """
                <!-- META-INF/persistence.xml (properties) -->
                <!-- TODO:
                <property name="hibernate.show_sql" value="true"/>
                <property name="hibernate.format_sql" value="true"/>
                <property name="hibernate.use_sql_comments" value="true"/>
                <property name="hibernate.generate_statistics" value="true"/>
                -->
                """;
        String logbackXml = """
                <!-- logback.xml (только для dev!) -->
                <!-- TODO:
                <logger name="org.hibernate.SQL" level="DEBUG"/>
                <logger name="org.hibernate.orm.jdbc.bind" level="TRACE"/>
                -->
                """;
        System.out.println(persistenceXml);
        System.out.println(logbackXml);
    }
}
