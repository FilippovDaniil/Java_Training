package m10_strategy_command.practice.task07;

class AddItemCommand implements OrderCommand {
    // TODO: поля Order order и long priceCents + конструктор
    //       AddItemCommand(Order order, long priceCents)

    @Override
    public void execute() {
        // TODO: order.addItem(priceCents)
    }

    @Override
    public void undo() {
        // TODO: order.removeLastItem()
    }
}
