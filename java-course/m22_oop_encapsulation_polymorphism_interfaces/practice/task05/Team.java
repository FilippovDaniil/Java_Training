package m22_oop_encapsulation_polymorphism_interfaces.practice.task05;

import java.util.ArrayList;
import java.util.List;

class Team {
    String name;
    // TODO: List<Player> players; конструктор (name, список); printRoster()
    private List<Player> players;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
