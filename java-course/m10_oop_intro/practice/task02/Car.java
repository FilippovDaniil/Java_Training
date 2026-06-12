package m10_oop_intro.practice.task02;

import lombok.*;


class Car {
    private String brand;

    public void start() {
        // TODO: выведите сообщение о запуске
        System.out.println("Zavoditsya: " + brand);
    }

    public Car(String brand) {
        this.brand = brand;
    }

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
