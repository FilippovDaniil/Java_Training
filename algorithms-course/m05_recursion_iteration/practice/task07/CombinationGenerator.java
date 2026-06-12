package m05_recursion_iteration.practice.task07;

/**
 * Модуль Data-Cruncher: генератор комбинаций параметров анализа.
 * Пример: выбрать k параметров из n доступных, перебрать все сочетания C(n, k).
 * Используется бэктрекинг (рекурсия с выбором/откатом).
 */
import java.util.ArrayList;
import java.util.List;

public class CombinationGenerator {

    /** Все сочетания по k элементов из списка items (порядок не важен). */
    public <T> List<List<T>> combinations(List<T> items, int k) {
        List<List<T>> result = new ArrayList<>();
        // TODO: backtrack(start=0, current=[]): если current.size()==k -> добавить копию;
        //       иначе перебирать i от start, брать items[i], углубляться (start=i+1), откатывать
        return result;
    }

    // TODO: вспомогательный рекурсивный метод backtrack(...)
}
