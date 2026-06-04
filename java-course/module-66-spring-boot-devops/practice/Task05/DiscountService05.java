import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountService05 {
    public long applyDiscount(long price, int percent) {
        return price - (price * percent / 100);
    }
}
