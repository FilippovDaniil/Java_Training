package m11_observer_chain_of_responsibility.practice.task03;

class LowHandler extends Handler {
    @Override
    public String handle(int n) {
        // TODO: если n < 10 → "low: " + n; иначе passToNext(n)
        return "";
    }
}
