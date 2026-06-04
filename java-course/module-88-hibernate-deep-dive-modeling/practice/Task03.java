/**
 * Задача 03 — Модуль 88: одно value object дважды через @AttributeOverride (Address)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.hibernate.orm:hibernate-core, com.h2database:h2 + META-INF/persistence.xml ("shop-pu").
 *
 * ЗАДАНИЕ:
 *   В Customer03 нужно хранить ДВА адреса одного типа Address03 — доставки и для счёта.
 *   По умолчанию колонки конфликтуют (city/street дважды). Разведите их @AttributeOverride.
 *
 *   1) Address03 — @Embeddable с полями city, street.
 *   2) В Customer03:
 *        @Embedded
 *        @AttributeOverrides({
 *            @AttributeOverride(name="city",   column=@Column(name="ship_city")),
 *            @AttributeOverride(name="street", column=@Column(name="ship_street"))
 *        })
 *        private Address03 shipping;
 *      и аналогично billing → bill_city / bill_street.
 *   3) Сохраните клиента с двумя разными адресами, перечитайте, выведите оба.
 *
 * ЦЕЛЬ: научиться встраивать один тип несколько раз без конфликта колонок.
 *
 * ПОДСКАЗКА: имена в @AttributeOverride(name=...) — это ПОЛЯ Address03 (city/street),
 *            а column — целевые имена колонок в таблице customers.
 */
import jakarta.persistence.*;

public class Task03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop-pu");
        EntityManager em = emf.createEntityManager();
        Long id;
        try {
            em.getTransaction().begin();
            Customer03 c = new Customer03("Иван",
                    new Address03("Москва", "Тверская 1"),
                    new Address03("Казань", "Баумана 5"));
            em.persist(c);
            em.getTransaction().commit();
            id = c.getId();
            em.clear();

            // TODO: Customer03 r = em.find(Customer03.class, id);
            // TODO: System.out.println("доставка: " + r.getShipping().getCity() + ", " + r.getShipping().getStreet());
            // TODO: System.out.println("счёт: " + r.getBilling().getCity() + ", " + r.getBilling().getStreet());
        } finally {
            em.close();
            emf.close();
        }
    }
}

@Embeddable
class Address03 {
    private String city;
    private String street;
    protected Address03() {}
    public Address03(String city, String street) { this.city = city; this.street = street; }
    public String getCity() { return city; }
    public String getStreet() { return street; }
}

@Entity @Table(name = "customers")
class Customer03 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // TODO: @Embedded @AttributeOverrides({ ship_city, ship_street })
    private Address03 shipping;

    // TODO: @Embedded @AttributeOverrides({ bill_city, bill_street })
    private Address03 billing;

    protected Customer03() {}
    public Customer03(String name, Address03 shipping, Address03 billing) {
        this.name = name; this.shipping = shipping; this.billing = billing;
    }
    public Long getId() { return id; }
    public Address03 getShipping() { return shipping; }
    public Address03 getBilling() { return billing; }
}
