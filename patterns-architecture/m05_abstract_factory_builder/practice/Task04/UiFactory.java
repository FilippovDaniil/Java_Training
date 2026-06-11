package m05_abstract_factory_builder.practice.task04;

// Абстрактная фабрика: создаёт всю согласованную семью продуктов.
interface UiFactory {
    Button createButton();
    Checkbox createCheckbox();
}
