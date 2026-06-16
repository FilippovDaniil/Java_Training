package m25_multithreading_concurrency.practice;

public class CountThread extends Thread{
    public void run(){
        for (int i = 0; i < 10; i++){
            System.out.println(getName() + ": " + i);
        }
    }
}
