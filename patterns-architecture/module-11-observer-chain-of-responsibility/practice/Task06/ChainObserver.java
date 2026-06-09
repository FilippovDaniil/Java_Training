// Подписчик, который пропускает событие через цепочку обработчиков (связка Observer + CoR).
class ChainObserver implements Observer {
    // TODO: поле Handler head + конструктор ChainObserver(Handler head)

    @Override
    public void onEvent(int level, String msg) {
        // TODO: делегировать head.handle(level, msg)
    }
}
