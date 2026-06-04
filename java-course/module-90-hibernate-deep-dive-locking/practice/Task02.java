/**
 * Задача 02 — Модуль 90: OPTIMISTIC_FORCE_INCREMENT (версионируем родителя)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *   hibernate.show_sql=true.
 *
 * ЗАДАНИЕ:
 *   Иногда меняется «ребёнок», но залочить логически надо «родителя» (агрегат).
 *   1) Order02 имеет @Version. Сохраните заказ (version=0).
 *   2) В транзакции загрузите заказ с LockModeType.OPTIMISTIC_FORCE_INCREMENT:
 *        em.find(Order02.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
 *      Даже НЕ меняя сам заказ (как будто меняем его строки), на commit версия
 *      ПРИНУДИТЕЛЬНО вырастет (0 → 1) — в логе будет UPDATE version.
 *   3) Перечитайте и убедитесь, что version == 1.
 *
 * ЦЕЛЬ: понять, зачем форсировать инкремент версии без изменения самой сущности.
 *
 * ПОДСКАЗКА: сравните с обычным OPTIMISTIC — тот версию НЕ поднимает, только проверяет.
 */
import jakarta.persistence.*;

public class Task02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Order02 o = new Order02("ORD-1");
            em.persist(o);
            em.getTransaction().commit();
            id = o.getId();
            em.clear();

            // TODO: em.getTransaction().begin();
            // TODO: Order02 locked = em.find(Order02.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            // TODO: // даже без изменения locked версия поднимется на commit
            // TODO: em.getTransaction().commit();
            // TODO: em.clear();
            // TODO: System.out.println("version = " + em.find(Order02.class, id).getVersion()); // 1
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Entity @Table(name = "orders")
class Order02 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Version
    private long version;
    protected Order02() {}
    public Order02(String number) { this.number = number; }
    public Long getId() { return id; }
    public long getVersion() { return version; }
}
