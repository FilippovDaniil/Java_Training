import java.util.ArrayList;
import java.util.List;

// Оркестратор перевода: при сбое откатывает успешные шаги в обратном порядке.
class TransferSaga {
    public void run(List<SagaStep> steps) {
        // TODO: List<SagaStep> done = new ArrayList<>();
        //       try { for (s : steps) { s.execute(); done.add(s); } }
        //       catch (Exception e) { for (i = done.size()-1; i>=0; i--) done.get(i).compensate(); }
    }
}
