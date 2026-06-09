// Абстрактная фабрика семейства объектов доступа к БД.
interface DbFactory {
    Connection createConnection();
    Query createQuery();
}
