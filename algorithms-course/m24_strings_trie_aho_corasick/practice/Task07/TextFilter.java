package m24_strings_trie_aho_corasick.practice.task07;

/**
 * Модуль Data-Cruncher: поиск запрещённых слов и автодополнение запросов.
 *  - addBannedWord / countBanned — поиск всех запрещённых слов в тексте
 *    (Ахо-Корасик: один проход для всего словаря);
 *  - addDictionaryWord / suggest — автодополнение по префиксу (Trie).
 */
import java.util.ArrayList;
import java.util.List;

public class TextFilter {

    // --- словарь запрещённых слов (Ахо-Корасик) ---
    public void addBannedWord(String word) {
        // TODO: добавить паттерн в бор Ахо-Корасик
    }
    public void buildFilter() {
        // TODO: построить fail-ссылки (после добавления всех запрещённых слов)
    }
    /** Сколько запрещённых слов встречается в тексте (с учётом всех вхождений). */
    public int countBanned(String text) {
        // TODO: один проход Ахо-Корасик
        return 0;
    }

    // --- автодополнение (Trie) ---
    public void addDictionaryWord(String word) {
        // TODO: вставить слово в Trie автодополнения
    }
    /** Подсказки: все слова словаря, начинающиеся с prefix. */
    public List<String> suggest(String prefix) {
        // TODO: пройти по префиксу в Trie, собрать слова поддерева
        return new ArrayList<>();
    }
}
