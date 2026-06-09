import java.util.ArrayList;
import java.util.List;

// Оркестратор: централизованно ведёт сагу, откатывает при сбое.
class SagaOrchestrator {
    public void run(List<SagaStep> steps) {
        // TODO: List<SagaStep> done = new ArrayList<>();
        //       try { for (s : steps) { s.execute(); done.add(s); } }
        //       catch (Exception e) { for (i = done.size()-1; i >= 0; i--) done.get(i).compensate(); }
    }
}
