/**
 * Задача 05 — Модуль 59: XML-конфигурация (ClassPathXmlApplicationContext)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   1) Создайте файл src/main/resources/beans.xml (или в classpath) со следующим
 *      содержимым (шаблон приведён в ПОДСКАЗКЕ).
 *   2) В main() поднимите контейнер через ClassPathXmlApplicationContext("beans.xml").
 *   3) Получите бин "productService" и вызовите listAll().
 *   4) Убедитесь, что поведение идентично Java-конфигурации из задач 02–03.
 *   5) Добавьте в beans.xml второй бин priceService с <constructor-arg ref="productRepository"/>.
 *      Получите его и вызовите getPrice("Ноутбук").
 *
 * ПОДСКАЗКА (содержимое beans.xml):
 *
 *   <?xml version="1.0" encoding="UTF-8"?>
 *   <beans xmlns="http://www.springframework.org/schema/beans"
 *          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *          xsi:schemaLocation="
 *            http://www.springframework.org/schema/beans
 *            https://www.springframework.org/schema/beans/spring-beans.xsd">
 *
 *       <bean id="productRepository"
 *             class="InMemoryProductRepository"/>
 *
 *       <bean id="productService"
 *             class="ProductService">
 *           <constructor-arg ref="productRepository"/>
 *       </bean>
 *
 *   </beans>
 *
 * ВАЖНО:
 *   В XML-конфиге class указывает полное имя класса (с пакетом, если есть).
 *   Здесь, так как классы без пакета — имя простое.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task05 {

    public static void main(String[] args) {
        // TODO 1: поднять контекст из XML
        //   ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        // TODO 2: получить бин "productService" по имени и типу
        // TODO 3: вызвать listAll()
        // TODO 4: получить priceService и вызвать getPrice("Ноутбук")
        // TODO 5: закрыть контекст
    }
}

// ============================================================
// Доменные классы (готовы — не менять)
// ============================================================

interface ProductRepository {
    java.util.List<String> findAll();
    java.util.Map<String, Double> getPriceMap();
}

class InMemoryProductRepository implements ProductRepository {
    @Override
    public java.util.List<String> findAll() {
        return java.util.List.of("Ноутбук", "Мышь", "Клавиатура");
    }

    @Override
    public java.util.Map<String, Double> getPriceMap() {
        return java.util.Map.of("Ноутбук", 75000.0, "Мышь", 1500.0, "Клавиатура", 3500.0);
    }
}

class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void listAll() {
        System.out.println("=== Список товаров (из XML-контекста) ===");
        repository.findAll().forEach(System.out::println);
    }
}

class PriceService {
    private final ProductRepository repository;

    public PriceService(ProductRepository repository) {
        this.repository = repository;
    }

    public double getPrice(String name) {
        double price = repository.getPriceMap().getOrDefault(name, -1.0);
        System.out.printf("Цена '%s': %.2f руб.%n", name, price);
        return price;
    }
}
