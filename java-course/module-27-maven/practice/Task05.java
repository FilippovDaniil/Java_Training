/**
 * Задача 05 — Модуль 27: Плагин компилятора (упражнение в терминале)
 *
 * ЗАДАНИЕ:
 *   Явно подключите maven-compiler-plugin в секцию <build> вашего
 *   pom.xml и задайте версию Java (17). Затем пересоберите проект.
 *
 * ФРАГМЕНТ pom.xml:
 *   <build>
 *       <plugins>
 *           <plugin>
 *               <groupId>org.apache.maven.plugins</groupId>
 *               <artifactId>maven-compiler-plugin</artifactId>
 *               <version>3.11.0</version>
 *               <configuration>
 *                   <source>17</source>
 *                   <target>17</target>
 *               </configuration>
 *           </plugin>
 *       </plugins>
 *   </build>
 *
 * ПРОВЕРКА:
 *   mvn clean compile проходит успешно с указанной версией компилятора.
 */
public class Task05 {
    public static void main(String[] args) {
        System.out.println("Выполните задание в терминале (см. комментарий выше).");
    }
}
