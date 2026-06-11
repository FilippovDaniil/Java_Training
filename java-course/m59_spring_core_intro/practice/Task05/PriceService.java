package m59_spring_core_intro.practice.task05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
