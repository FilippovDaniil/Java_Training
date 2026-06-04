/**
 * Задача 04 — Модуль 111: жизненный цикл (stop, start, rm, rmi, prune)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Пройдите полный жизненный цикл контейнера web:
 *   1) Остановите: docker stop web   (контейнер виден в docker ps -a как Exited)
 *   2) Запустите снова: docker start web   (тот же контейнер, не новый!)
 *   3) Снова остановите и удалите КОНТЕЙНЕР: docker stop web ; docker rm web
 *   4) Удалите ОБРАЗ: docker rmi nginx
 *   5) Очистка мусора (остановленные контейнеры, неиспользуемые образы): docker system prune
 *   6) Впишите команды в COMMANDS ниже.
 *
 * ЦЕЛЬ: различать остановку/удаление контейнера и удаление образа; чистить мусор.
 *
 * ТАБЛИЦА:
 *   stop  → контейнер остановлен (но существует, виден в ps -a)
 *   start → запустить ТОТ ЖЕ контейнер заново (run создаёт НОВЫЙ)
 *   rm    → удалить контейнер;  rmi → удалить образ
 *
 * ПОДСКАЗКА: docker rm -f останавливает и удаляет за один шаг; prune освобождает место.
 */
public class Task04 {
    public static void main(String[] args) {
        String commands = """
                # TODO: впишите команды жизненного цикла
                # docker stop web
                # docker start web        # тот же контейнер (НЕ новый)
                # docker stop web
                # docker rm web           # удалить контейнер
                # docker rmi nginx        # удалить образ
                # docker system prune     # очистить остановленные/неиспользуемые
                """;
        System.out.println(commands);
    }
}
