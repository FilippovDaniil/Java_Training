import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** ✅ Реализуйте корректные версии. */
class GoodService05 {
    private final EntityManager em;
    GoodService05(EntityManager em) { this.em = em; }

    int totalLines() {
        // TODO: JOIN FETCH (один запрос) ИЛИ агрегат "select sum(size(o.lines)) ..." / count
        return 0;
    }

    void raiseAll(int delta) {
        // TODO: bulk "update Order05 o set o.total = o.total + :d" + em.clear()
    }
}
