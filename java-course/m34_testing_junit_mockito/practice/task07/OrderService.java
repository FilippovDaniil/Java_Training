package m34_testing_junit_mockito.practice.task07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderService {
    private final Inventory inventory;
    private final PaymentGateway gateway;
    private final Notifier notifier;

    OrderService(Inventory inventory, PaymentGateway gateway, Notifier notifier) {
        this.inventory = inventory;
        this.gateway = gateway;
        this.notifier = notifier;
    }

    boolean order(String product, double price) {
        if (!inventory.inStock(product)) {
            return false;
        }
        if (!gateway.charge(price)) {
            return false;
        }
        inventory.reduce(product);
        notifier.notify("Заказ оформлен: " + product);
        return true;
    }
}
