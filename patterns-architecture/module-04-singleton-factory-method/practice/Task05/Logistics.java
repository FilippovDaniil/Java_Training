abstract class Logistics {

    // фабричный метод — что создать, решает подкласс
    protected abstract Transport createTransport();

    // общая логика, одинаковая для всех видов логистики (не переопределяется)
    public String planDelivery() {
        // TODO: создать транспорт через createTransport() и вернуть
        //       "План: " + transport.deliver()
        return "";
    }
}
