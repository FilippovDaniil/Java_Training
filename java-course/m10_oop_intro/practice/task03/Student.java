package m10_oop_intro.practice.task03;



class Student {
    private String name;

    public void printInfo() {
        System.out.println("Студент: " + name);
    }

    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
