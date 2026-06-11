package m11_observer_chain_of_responsibility.practice.task01;

class EmailSubscriber implements Observer {
    @Override
    public void update(String news) {
        // TODO: напечатать "email: " + news
    }
}
