package m11_observer_chain_of_responsibility.practice.task04;

class TeamLead extends Approver {
    @Override
    public String approve(int amount) {
        // TODO: amount <= 1000 → "TeamLead одобрил " + amount; иначе passToNext(amount)
        return "";
    }
}
