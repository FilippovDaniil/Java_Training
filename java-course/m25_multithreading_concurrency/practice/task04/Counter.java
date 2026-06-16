package m25_multithreading_concurrency.practice.task04;

class Counter {
    private int count = 0;

    // TODO: метод increment() (сначала без synchronized, потом с ним)

    public synchronized void increment(){
        count++;
    }

    public int getCount() {
        return count;
    }
}
