package m05_abstract_factory_builder.practice.task05;

class PostgresFactory implements DbFactory {
    @Override
    public Connection createConnection() {
        // TODO: new PostgresConnection()
        return null;
    }

    @Override
    public Query createQuery() {
        // TODO: new PostgresQuery()
        return null;
    }
}
