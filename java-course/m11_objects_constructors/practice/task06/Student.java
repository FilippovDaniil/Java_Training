package m11_objects_constructors.practice.task06;

class Student {
    // TODO: private поля, конструктор, геттеры, сеттер gpa с валидацией, toString
    private String name;
    private String group;
    private double gpa;

    public Student(String name, String group, double gpa) {
        this.name = name;
        this.group = group;
        this.gpa = gpa;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 5.0){
            System.out.println("Enter the right number");
            return;
        } else {
            this.gpa = gpa;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
