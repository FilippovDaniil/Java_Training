package m12_classes_static.practice.task02;

class MathUtils {
    // TODO: статические методы max, min, isEven

    public static int max(int a, int b){
        return (a > b) ? a : b;
    }

    public static int min(int a, int b){
        return (a < b) ? a : b;
    }

    public static boolean isEven(int a){
        return a % 2 == 0;
    }
}
