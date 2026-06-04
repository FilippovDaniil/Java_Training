/**
 * Задача 05 — Модуль 118: чек-лист production-готовности
 *
 * ФОРМАТ: носитель артефакта (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Пройдите ваш шаблон по чек-листу прод-готовности и отметьте выполненные пункты (замените [ ] на [x]).
 *   Заполните CHECKLIST ниже по своему проекту:
 *     ОБРАЗ:
 *       [ ] multi-stage / layered jars (компилятор не в рантайме)
 *       [ ] минимальная база (JRE-alpine / distroless)
 *       [ ] non-root (USER)
 *       [ ] JVM container-aware (MaxRAMPercentage, JDK 17)
 *       [ ] HEALTHCHECK (Actuator)
 *       [ ] просканирован на CVE (docker scout / trivy)
 *       [ ] фиксированные теги (не latest)
 *     КОНФИГ:
 *       [ ] один образ — много сред (env)
 *       [ ] секреты вне образа и вне git
 *       [ ] профиль через SPRING_PROFILES_ACTIVE
 *       [ ] логи в stdout
 *     ДАННЫЕ/ОРКЕСТРАЦИЯ:
 *       [ ] данные в томах
 *       [ ] depends_on + healthcheck
 *       [ ] лимиты памяти/CPU
 *       [ ] поднимается одной командой
 *
 * ЦЕЛЬ: систематически проверить, что ничего не упущено перед прод-релизом.
 *
 * ВАЖНО: каждый невыполненный пункт — потенциальный инцидент на проде. Проходить чек-лист целиком.
 *
 * ПОДСКАЗКА: соотнесите пункты с модулями: образ (112/113/117), конфиг (114), оркестрация (115/116).
 */
public class Task05 {
    public static void main(String[] args) {
        String checklist = """
                # TODO: отметьте выполненные пункты ([ ] → [x]) по вашему проекту
                # ОБРАЗ:
                #   [ ] multi-stage / layered jars
                #   [ ] минимальная база (JRE-alpine / distroless)
                #   [ ] non-root (USER)
                #   [ ] JVM container-aware (MaxRAMPercentage)
                #   [ ] HEALTHCHECK (Actuator)
                #   [ ] просканирован на CVE
                #   [ ] фиксированные теги
                # КОНФИГ:
                #   [ ] один образ — много сред (env)
                #   [ ] секреты вне образа и вне git
                #   [ ] профиль через SPRING_PROFILES_ACTIVE
                #   [ ] логи в stdout
                # ДАННЫЕ/ОРКЕСТРАЦИЯ:
                #   [ ] данные в томах
                #   [ ] depends_on + healthcheck
                #   [ ] лимиты памяти/CPU
                #   [ ] поднимается одной командой
                """;
        System.out.println(checklist);
    }
}
