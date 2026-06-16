package m22_oop_encapsulation_polymorphism_interfaces.practice.task05;

import java.util.ArrayList;
import java.util.List;

class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
