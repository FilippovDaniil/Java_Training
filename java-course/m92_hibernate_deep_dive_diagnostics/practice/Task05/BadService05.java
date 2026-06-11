package m92_hibernate_deep_dive_diagnostics.practice.task05;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** ❌ Намеренно плохой сервис — найдите анти-паттерны. */
class BadService05 {
    private final EntityManager em;
    BadService05(EntityManager em) { this.em = em; }

    // TODO: АНТИ-ПАТТЕРН 1 (?): ____________
    int totalLines() {
        int sum = 0;
        for (Order05 o : em.createQuery("select o from Order05 o", Order05.class).getResultList()) {
            sum += o.getLines().size();   // lazy в цикле
        }
        return sum;
    }

    // TODO: АНТИ-ПАТТЕРН 2 (?): ____________
    void raiseAll(int delta) {
        for (Order05 o : em.createQuery("select o from Order05 o", Order05.class).getResultList()) {
            o.setTotal(o.getTotal() + delta);
            em.merge(o);   // TODO: нужно ли merge для managed? + построчно вместо bulk
        }
    }
}
