package m12_classes_static.practice.task04;

class Game {
    public static int totalGames = 0;
    private int score = 0;

    // TODO: конструктор (totalGames++) и метод addPoints(int p)


    public Game(int score) {
        this.score = score;
        totalGames++;
    }

    public void addPoints(int p){
        this.score = this.score + p;
    }

    @Override
    public String toString() {
        return "Game{" +
                "score=" + score +
                '}';
    }
}
