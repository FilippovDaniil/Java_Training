package m12_classes_static.practice.task06;

class Computer {
    private String brand;

    static class Processor {
        String model;
        int cores;

        // TODO: метод info()


        public Processor(String model, int cores) {
            this.model = model;
            this.cores = cores;
        }

        public void info(){
            System.out.println("Model: " + model + ", Cores: " + cores);
        }
    }

    public Computer() {
    }
}
