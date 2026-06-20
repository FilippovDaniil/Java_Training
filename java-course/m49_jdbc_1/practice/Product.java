package m49_jdbc_1.practice;

import java.math.BigDecimal;

public record Product(long id, String name, BigDecimal price, int quantity) {

    Product(String name, BigDecimal price, int quantity) {
        this(0, name, price, quantity);
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, name='%s', price=%s, qty=%d]",
                id, name, price, quantity);
    }
}
