import java.util.ArrayList;
import java.util.List;

interface OrderObserver {
    void onNewOrder(String description);
}
