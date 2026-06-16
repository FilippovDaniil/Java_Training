package m23_oop_overloading_overriding_abstract.practice.task01;

class Calculator {
    // TODO: три перегруженных метода sum
    public int sum(int a, int b){
        return a + b;
    }

    public double sum(double a, double b){
        return a + b;
    }

    public int sum(int a, int b, int c){
        return a + b + c;
    }


}
