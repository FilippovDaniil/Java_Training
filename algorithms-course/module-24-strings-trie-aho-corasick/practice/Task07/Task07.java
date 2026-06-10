/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 24: запрещённые слова + автодополнение
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher TextFilter (файл TextFilter.java):
 *     - поиск запрещённых слов в тексте по словарю (Ахо-Корасик, один проход);
 *     - автодополнение поискового запроса по префиксу (Trie).
 *
 * ПРИМЕР / ВЫВОД:
 *   запрещённые: "spam","ad"
 *   текст "this spam has an ad and spam" -> запрещённых найдено: 3 (spam, ad, spam)
 *   словарь автодополнения: "search","sort","select"
 *   suggest("se") -> [search, select]
 *
 * ТРЕБОВАНИЯ:
 *   - countBanned — Ахо-Корасик (все вхождения всех запрещённых слов);
 *   - suggest — Trie-автодополнение по префиксу;
 *   - две независимые структуры внутри одного фильтра.
 *
 * ПОДСКАЗКА:
 *   Запрещённые слова — много паттернов сразу -> Ахо-Корасик; префикс -> Trie.
 */

public class Task07 {
    public static void main(String[] args) {
        TextFilter f = new TextFilter();
        for (String w : new String[]{"spam", "ad"}) f.addBannedWord(w);
        f.buildFilter();
        System.out.println("запрещённых: " + f.countBanned("this spam has an ad and spam"));

        for (String w : new String[]{"search", "sort", "select"}) f.addDictionaryWord(w);
        System.out.println("suggest(se): " + f.suggest("se"));
    }
}
