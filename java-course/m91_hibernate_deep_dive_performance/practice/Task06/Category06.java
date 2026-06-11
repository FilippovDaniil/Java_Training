package m91_hibernate_deep_dive_performance.practice.task06;

import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

@Entity @Table(name = "categories")
// TODO: @Cacheable
// TODO: @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class Category06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    protected Category06() {}
    public Category06(String name) { this.name = name; }
    public Long getId() { return id; }
}
