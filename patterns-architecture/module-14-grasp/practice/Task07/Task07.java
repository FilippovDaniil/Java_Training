/**
 * Задача 07 — Тема 14 (МИНИ-ПРОЕКТ OPS): оформление заказа по GRASP
 *
 * Развитие OPS. Соберите оформление заказа, применив сразу несколько принципов GRASP:
 *   - INFORMATION EXPERT — Order сам считает свой total;
 *   - CREATOR — Order создаёт свои OrderLine;
 *   - PURE FABRICATION + LOW COUPLING — OrderRepository (интерфейс) хранит заказы,
 *     сервис зависит от абстракции;
 *   - CONTROLLER — тонкий OrderController координирует, логика в OrderService.
 *
 * ЗАДАНИЕ:
 *   1. OrderLine (файл OrderLine.java): sku, qty, priceCents; subtotal().
 *   2. Order (файл Order.java): id, список строк; addLine(sku, qty, price)
 *      создаёт OrderLine (Creator); total() суммирует (Information Expert); getId().
 *   3. OrderRepository (файл OrderRepository.java) — интерфейс (Pure Fabrication):
 *      save(Order), findById(String); InMemoryOrderRepository (файл ...).
 *   4. OrderService (файл OrderService.java): зависит от OrderRepository (Low
 *      Coupling); createOrder(id), addItem(id, sku, qty, price), total(id) — логика.
 *   5. OrderController (файл OrderController.java): делегирует сервису (тонкий).
 *   В main: через контроллер создайте заказ, добавьте 2 позиции, выведите итог.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Итог заказа A-1: 32000   (2*10000 + 3*4000)
 *
 * ТРЕБОВАНИЯ:
 *   - Order — эксперт по своей сумме и создатель своих строк;
 *   - OrderService зависит от интерфейса репозитория (не от реализации);
 *   - OrderController не содержит бизнес-логики — только координация;
 *   - хранение вынесено в Pure Fabrication (OrderRepository).
 *
 * ПОДСКАЗКА:
 *   Это «каркас» будущего слоистого приложения (тема 15) и гексагона (тема 17):
 *   контроллер → сервис → репозиторий, домен в центре.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите controller → service → InMemoryOrderRepository;
        //       createOrder("A-1"); addItem дважды; выведите total("A-1")
    }
}
