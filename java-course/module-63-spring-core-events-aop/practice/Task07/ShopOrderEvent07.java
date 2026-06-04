import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// ============================================================
// Событие заказа (готово)
// ============================================================
class ShopOrderEvent07 {
    private final Object source;
    private final String customerId;
    private final String product;

    public ShopOrderEvent07(Object source, String customerId, String product) {
        this.source = source;
        this.customerId = customerId;
        this.product = product;
    }

    public String getCustomerId() { return customerId; }
    public String getProduct()    { return product; }
    public Object getSource()     { return source; }
}
