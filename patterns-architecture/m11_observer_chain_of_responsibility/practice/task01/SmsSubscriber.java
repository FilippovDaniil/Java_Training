package m11_observer_chain_of_responsibility.practice.task01;

class SmsSubscriber implements Observer {
    @Override
    public void update(String news) {
        // TODO: напечатать "sms: " + news
    }
}
