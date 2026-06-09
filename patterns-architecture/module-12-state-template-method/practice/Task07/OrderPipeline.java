// Template Method: фиксированный порядок проводки заказа по состояниям.
abstract class OrderPipeline {
    public final String run(Order o) {
        // TODO: собрать строки:
        //   format(o.status())   // NEW
        //   o.pay();  format(o.status())   // PAID
        //   o.ship(); format(o.status())   // SHIPPED
        // объединить через "\n"
        return "";
    }

    protected abstract String format(String status);
}
