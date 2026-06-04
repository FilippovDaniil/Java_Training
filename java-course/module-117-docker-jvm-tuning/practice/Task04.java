/**
 * Задача 04 — Модуль 117: гигиена образа (выбор базы, размер, сканирование)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Сравните базовые образы по размеру и просканируйте на уязвимости:
 *   1) Соберите вариант на разных базах (меняя FROM) и сравните:
 *        docker images | grep tasktracker
 *      Ориентиры: jdk (~450МБ) > jre (~270МБ) > jre-alpine (~170МБ).
 *   2) Просканируйте образ на CVE:
 *        docker scout cves tasktracker:1.0.0       # (или: trivy image tasktracker:1.0.0)
 *   3) Сделайте вывод: для рантайма — JRE (не JDK), база — alpine/distroless, меньше пакетов = меньше CVE.
 *   4) Впишите команды и вывод в COMMANDS ниже.
 *
 * ЦЕЛЬ: усвоить принципы гигиены — минимальная база, JRE вместо JDK, регулярное сканирование.
 *
 * ТАБЛИЦА БАЗ:
 *   eclipse-temurin:17-jdk        ~450 МБ (компилятор — рантайму не нужен)
 *   eclipse-temurin:17-jre        ~270 МБ
 *   eclipse-temurin:17-jre-alpine ~170 МБ
 *   distroless/java17             ~230 МБ (без shell/пакетного менеджера)
 *
 * ПОДСКАЗКА: меньше пакетов в образе → меньше поверхность атаки и число CVE.
 */
public class Task04 {
    public static void main(String[] args) {
        String commands = """
                # TODO: сравнение баз и сканирование
                # docker images | grep tasktracker
                # docker scout cves tasktracker:1.0.0      # или trivy image tasktracker:1.0.0
                #
                # Вывод:
                #   рантайм → JRE (не JDK);
                #   база → alpine/distroless;
                #   меньше пакетов → меньше CVE.
                """;
        System.out.println(commands);
    }
}
