package m11_observer_chain_of_responsibility.practice.task06;

class InfoHandler extends Handler {
    @Override
    public void handle(int level, String msg) {
        // TODO: если level == 1 → напечатать "INFO: " + msg; иначе passToNext(level, msg)
    }
}
