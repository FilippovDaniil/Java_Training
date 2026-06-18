package m30_design_patterns.practice.task05;

import lombok.ToString;

@ToString
public class EmailSubscriber implements Observer{
    @Override
    public void update(String news) {
        System.out.println("Получено сообщение: " + news + " через email");
    }
}
