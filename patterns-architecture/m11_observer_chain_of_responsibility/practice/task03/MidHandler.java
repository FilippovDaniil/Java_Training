package m11_observer_chain_of_responsibility.practice.task03;

class MidHandler extends Handler {
    @Override
    public String handle(int n) {
        // TODO: если n < 100 → "mid: " + n; иначе passToNext(n)
        return "";
    }
}
