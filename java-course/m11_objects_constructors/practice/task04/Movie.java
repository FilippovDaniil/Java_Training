package m11_objects_constructors.practice.task04;

class Movie {
    String title;
    String director;
    int year;

    Movie(String title, String director, int year) {
        this.title = title;
        this.director = director;
        this.year = year;
    }

    // TODO: переопределите toString()


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                '}';
    }
}
