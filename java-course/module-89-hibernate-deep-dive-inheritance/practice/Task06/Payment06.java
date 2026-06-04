import jakarta.persistence.*;
import java.util.List;

@Entity @Table(name = "payments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type")
abstract class Payment06 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long amount;
    protected Payment06() {}
    protected Payment06(long amount) { this.amount = amount; }
}
