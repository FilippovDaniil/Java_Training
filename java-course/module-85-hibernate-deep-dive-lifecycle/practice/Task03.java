/**
 * Задача 03 — Модуль 85: detached и merge (повторное присоединение)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   1) Сохраните Product03("Сыр", 300). Получите объект и сделайте его detached:
 *      em.detach(p) (или em.clear()).
 *   2) Измените detached-объект: p.setPrice(250). Убедитесь, что em.contains(p) == false.
 *      В новой транзакции перечитайте — цена ещё 300 (изменение detached НЕ сохранилось).
 *   3) Присоедините обратно: Product03 managed = em.merge(p) ВНУТРИ транзакции, commit.
 *      Перечитайте — теперь цена 250.
 *   4) Подчеркните ловушку: merge ВОЗВРАЩАЕТ новый managed-объект; исходный p остался detached
 *      (p != managed). Выведите это сравнение.
 *
 * ЦЕЛЬ: понять detached-состояние и корректное использование merge.
 *
 * ПОДСКАЗКА: работать дальше нужно с объектом, который ВЕРНУЛ merge, а не с исходным.
 */
import jakarta.persistence.*;

public class Task03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Product03 p = new Product03("Сыр", 300);
            em.persist(p);
            em.getTransaction().commit();
            id = p.getId();

            // TODO: em.detach(p);  // теперь p — detached
            // TODO: p.setPrice(250);
            // TODO: System.out.println("contains(detached) = " + em.contains(p)); // false

            // TODO: em.getTransaction().begin();
            // TODO: System.out.println("в БД пока = " + em.find(Product03.class, id).getPrice()); // 300
            // TODO: Product03 managed = em.merge(p);   // присоединить копию
            // TODO: System.out.println("p == managed ? " + (p == managed)); // false
            // TODO: em.getTransaction().commit();

            // TODO: System.out.println("после merge = " + em.find(Product03.class, id).getPrice()); // 250
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "products")
class Product03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    protected Product03() {}
    public Product03(String name, int price) { this.name = name; this.price = price; }
    public Long getId() { return id; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
