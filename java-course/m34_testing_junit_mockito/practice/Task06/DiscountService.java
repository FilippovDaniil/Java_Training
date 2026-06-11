package m34_testing_junit_mockito.practice.task06;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountService {
    private final PriceRepository repo;
    DiscountService(PriceRepository repo) { this.repo = repo; }
    double finalPrice(long productId, int discountPercent) {
        double base = repo.getPrice(productId);
        return base * (1 - discountPercent / 100.0);
    }
}
