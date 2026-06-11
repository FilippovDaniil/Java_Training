package m05_abstract_factory_builder.practice.task05;

// Абстрактная фабрика семейства объектов доступа к БД.
interface DbFactory {
    Connection createConnection();
    Query createQuery();
}
