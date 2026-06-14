package m11_objects_constructors.practice.task05;

import java.util.Objects;

class Book {
    String title;
    int year;

    Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    // TODO: переопределите equals() и hashCode()


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return year == book.year && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year);
    }
}
