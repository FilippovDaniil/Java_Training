package m13_lists_generics.practice.task07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TodoItem {
    String title;
    boolean done;

    TodoItem(String title) {
        this.title = title;
        this.done = false;
    }
}
