package m30_design_patterns.practice.task01;

public class Database {
    // TODO: реализуйте Singleton (приватный конструктор, INSTANCE, getInstance, query)
    private Database() {

    }

    private static final Database INSTANCE = new Database();

    public static Database getInstance() {
        return INSTANCE;
    }
}
