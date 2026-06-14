package m13_lists_generics.practice.task07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TodoItem {
    private String title;
    private boolean done;

    TodoItem(String title, boolean done) {
        this.title = title;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "title='" + title + '\'' +
                ", done=" + done +
                '}';
    }
}
