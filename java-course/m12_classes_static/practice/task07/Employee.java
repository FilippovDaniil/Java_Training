package m12_classes_static.practice.task07;

class Employee {
    // TODO: static nextId; private поля id, name, salary;
    //       конструктор с авто-ID; геттеры; toString; static getTotalEmployees()
    private int id = 0;
    private String name;
    private double salary;
    public static int nextId = 0;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.id = nextId;
        //System.out.println("test"); second
    }

    {
        nextId++;
        //System.out.println("check"); first
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
