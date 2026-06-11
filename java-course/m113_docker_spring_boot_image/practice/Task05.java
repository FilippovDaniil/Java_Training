package m113_docker_spring_boot_image.practice;

/**
 * Задача 05 — Модуль 113: сравнение размеров образов (один слой vs layered vs buildpacks)
 *
 * ФОРМАТ: носитель команд (.java + text-блок + println, компилируется bare-javac).
 *
 * ЗАДАНИЕ:
 *   Соберите образ тремя способами и сравните размеры/слои:
 *   1) «толстый» (один слой): Dockerfile из модуля 112 (COPY app.jar) → tasktracker:fat
 *   2) layered jars (задача 02) → tasktracker:layered
 *   3) buildpacks (задача 03) → tasktracker:bp
 *   Команды сравнения:
 *        docker images | grep tasktracker        # размеры
 *        docker history tasktracker:layered       # слои образа и их вес
 *   Сделайте вывод: какой слой «толстый», какой пересобирается при правке кода.
 *   Впишите команды и наблюдения в COMMANDS ниже.
 *
 * ЦЕЛЬ: на практике увидеть эффект расслоения — где кэшируется, а где меняется.
 *
 * ПОДСКАЗКА: docker history показывает вклад каждого слоя в размер образа.
 */
public class Task05 {
    public static void main(String[] args) {
        String commands = """
                # TODO: соберите 3 варианта и сравните
                # docker build -t tasktracker:fat .          # один слой (модуль 112)
                # docker build -t tasktracker:layered .       # layered (задача 02)
                # ./gradlew bootBuildImage --imageName=tasktracker:bp   # buildpacks (задача 03)
                #
                # docker images | grep tasktracker
                # docker history tasktracker:layered
                #
                # Вывод: слой dependencies — «толстый», кэшируется;
                #        слой application (код) — тонкий, пересобирается при правке.
                """;
        System.out.println(commands);
    }
}
