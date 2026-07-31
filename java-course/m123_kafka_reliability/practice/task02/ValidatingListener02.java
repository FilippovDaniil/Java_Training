package m123_kafka_reliability.practice.task02;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Слушатель, различающий ошибку данных и ошибку инфраструктуры.
 *
 * TODO 1: валидация — если значение пустое или не начинается с "{", бросить
 *         IllegalArgumentException("некорректный payload") (НЕ повторяемая).
 * TODO 2: если значение содержит "db-down", бросить RuntimeException("БД недоступна")
 *         (повторяемая).
 * TODO 3: иначе — напечатать «обработано» и время обработки.
 *
 * ПРИНЦИП: тип исключения — это сигнал инфраструктуре. Бросая RuntimeException там, где данные
 *   заведомо испорчены, вы заказываете бессмысленные повторы; бросая IllegalArgumentException
 *   при недоступной БД — теряете событие, которое можно было спокойно повторить.
 */
@Component
public class ValidatingListener02 {

    @KafkaListener(topics = "task.created", groupId = "backoff-demo")
    public void on(String value) {
        // TODO 1-3
    }
}
