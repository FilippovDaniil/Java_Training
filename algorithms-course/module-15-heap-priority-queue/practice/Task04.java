/**
 * Задача 04 — Тема 15: decreaseKey (уменьшение ключа)
 *
 * ЗАДАНИЕ:
 *   Добавьте в min-heap операцию decreaseKey(index, newValue): уменьшить
 *   значение элемента по позиции и восстановить свойство кучи через siftUp.
 *   Эта операция нужна в алгоритме Дейкстры (тема 19).
 *
 * ПРИМЕР / ВЫВОД:
 *   куча из [1, 3, 8, 5, 4] (валидная min-heap)
 *   decreaseKey(индекс элемента со значением 8, новое значение 0)
 *   extractMin -> 0   (новый минимум всплыл наверх)
 *
 * ТРЕБОВАНИЯ:
 *   - newValue должен быть НЕ больше текущего (decrease!) — иначе исключение;
 *   - после уменьшения — siftUp(index);
 *   - extractMin после decreaseKey возвращает новый минимум.
 *
 * ПОДСКАЗКА:
 *   Уменьшение значения может нарушить свойство только «вверх» -> siftUp, не siftDown.
 */

public class Task04 {

    static class MinHeap {
        private final int[] a; private int size;
        MinHeap(int cap){ a=new int[cap]; }
        void insert(int v){ a[size]=v; siftUp(size); size++; }
        int extractMin(){ int m=a[0]; a[0]=a[--size]; siftDown(0); return m; }
        void siftUp(int i){ while(i>0 && a[(i-1)/2]>a[i]){ int t=a[i];a[i]=a[(i-1)/2];a[(i-1)/2]=t; i=(i-1)/2; } }
        void siftDown(int i){
            while(2*i+1<size){ int c=2*i+1; if(c+1<size&&a[c+1]<a[c])c++; if(a[i]<=a[c])break;
                int t=a[i];a[i]=a[c];a[c]=t; i=c; }
        }
        void decreaseKey(int index, int newValue) {
            // TODO: проверить newValue <= a[index]; присвоить; siftUp(index)
        }
        int indexOf(int value){ for(int i=0;i<size;i++) if(a[i]==value) return i; return -1; }
    }

    public static void main(String[] args) {
        MinHeap h = new MinHeap(16);
        for (int v : new int[]{1, 3, 8, 5, 4}) h.insert(v);
        h.decreaseKey(h.indexOf(8), 0);
        System.out.println("extractMin = " + h.extractMin());   // 0
    }
}
