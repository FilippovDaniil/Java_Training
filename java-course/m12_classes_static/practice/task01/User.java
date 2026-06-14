package m12_classes_static.practice.task01;

public class User {
    private String name;
    // TODO: статическое поле count и конструктор, увеличивающий его
    public static int count = 0;

    public User(String name) {
        this.name = name;
        count++;
    }
}
