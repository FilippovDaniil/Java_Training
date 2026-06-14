package m14_collections_set_iterator.practice.task06;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class City {
    String name;
    String country;

    City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    // TODO: переопределите equals() и hashCode()


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        City city = (City) object;
        return Objects.equals(name, city.name) && Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
