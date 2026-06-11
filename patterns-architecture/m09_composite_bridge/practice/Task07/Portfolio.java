package m09_composite_bridge.practice.task07;

import java.util.ArrayList;
import java.util.List;

// Композит: портфель из счетов и вложенных портфелей.
class Portfolio implements AccountComponent {
    // TODO: поле name + список List<AccountComponent> children + конструктор Portfolio(String name)

    public void add(AccountComponent component) {
        // TODO: добавить в children
    }

    @Override
    public String name() {
        // TODO
        return "";
    }

    @Override
    public long totalBalanceCents() {
        // TODO: рекурсивно просуммировать totalBalanceCents() всех children
        return 0;
    }
}
