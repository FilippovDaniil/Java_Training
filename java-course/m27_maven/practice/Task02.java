package m27_maven.practice;

/**
 * Задача 02 — Модуль 27: Первый pom.xml (упражнение в терминале)
 *
 * ЗАДАНИЕ:
 *   В корне проекта my-app создайте файл pom.xml с минимальным
 *   содержимым: modelVersion, координаты (groupId/artifactId/version)
 *   и properties с версией Java 17 и кодировкой UTF-8.
 *
 * ШАБЛОН pom.xml:
 *   <project xmlns="http://maven.apache.org/POM/4.0.0">
 *       <modelVersion>4.0.0</modelVersion>
 *       <groupId>com.example</groupId>
 *       <artifactId>my-app</artifactId>
 *       <version>1.0.0</version>
 *       <properties>
 *           <maven.compiler.source>17</maven.compiler.source>
 *           <maven.compiler.target>17</maven.compiler.target>
 *           <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
 *       </properties>
 *   </project>
 *
 * ПРОВЕРКА:
 *   mvn validate завершается успешно (BUILD SUCCESS).
 */
public class Task02 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
    }
}
