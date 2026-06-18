package m30_design_patterns.practice.task07;

import java.util.ArrayList;
import java.util.List;

interface OrderObserver {
    void onNewOrder(String description, double price);
}
