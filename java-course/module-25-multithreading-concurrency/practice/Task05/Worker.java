class Worker implements Runnable {
    private volatile boolean running = true;

    public void run() {
        // TODO: цикл while(running) с работой
    }

    public void stop() {
        running = false;
    }
}
