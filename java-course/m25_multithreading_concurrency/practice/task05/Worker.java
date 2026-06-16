package m25_multithreading_concurrency.practice.task05;

class Worker implements Runnable {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("работаю...");
            try {
                Thread.sleep(200); // небольшая пауза, чтобы не захлебнуться выводом
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stop() {
        running = false;
    }
}
