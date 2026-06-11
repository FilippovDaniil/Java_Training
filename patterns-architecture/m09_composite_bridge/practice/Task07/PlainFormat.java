package m09_composite_bridge.practice.task07;

class PlainFormat implements StatementFormat {
    @Override
    public String format(AccountComponent node) {
        // TODO: node.name() + ": " + node.totalBalanceCents()
        return "";
    }
}
