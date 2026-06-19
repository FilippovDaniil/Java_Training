package m35_logging.practice;

/**
 * Задача 02 — Модуль 35: Параметризованные сообщения {}
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: slf4j-api + logback-classic.
 *
 * ЗАДАНИЕ:
 *   Залогируйте обработку нескольких заказов в цикле, используя
 *   параметризацию {} (НЕ конкатенацию строк):
 *     - info: "Обработка заказа {} клиента {}";
 *     - в зависимости от суммы — warn для крупных заказов (> 100000):
 *       "Крупный заказ {}: сумма {}".
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   INFO  - Обработка заказа 1 клиента Иван
 *   WARN  - Крупный заказ 2: сумма 150000
 *
 * ПОДСКАЗКА:
 *   log.info("Обработка заказа {} клиента {}", id, name);
 *   Используйте {} вместо "+": подстановка ленивая и эффективная.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task02 {
    private static final Logger log = LoggerFactory.getLogger(Task02.class);

    public static void main(String[] args) {
        // Залогируйте обработку заказов с параметрами {}
        String name1 = "Daniil";
        String name2 = "Ivan";
        int sum_order_1 = 1_000_000;
        int sum_order_2 = 1_000;

        Map<Integer, String> map = new HashMap<>();
        map.put(sum_order_1,name1);
        map.put(sum_order_2,name2);

        for (Map.Entry<Integer, String> entry : map.entrySet()){
            if (entry.getKey() < 10_000){
                log.info("Обработка заказа, пользователя {}, на сумму {}", entry.getValue(), entry.getKey());
            }else{
                log.warn("Крупный заказ, пользователя {}, на сумму {}", entry.getValue(), entry.getKey());
            }
        }


    }
}
