class Director extends Approver {
    @Override
    public String approve(int amount) {
        // TODO: amount <= 100000 → "Director одобрил " + amount; иначе passToNext(amount)
        return "";
    }
}
