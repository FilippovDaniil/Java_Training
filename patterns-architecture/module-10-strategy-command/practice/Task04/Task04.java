/**
 * Задача 04 — Тема 10: Command с отменой (undo)
 *
 * ЗАДАНИЕ:
 *   Сделайте текстовый редактор с отменой через команды:
 *     - интерфейс Command (файл Command.java): void execute(); void undo();
 *     - TextDocument (файл TextDocument.java): append(String), deleteLast(int n),
 *       String content();
 *     - AddTextCommand (файл AddTextCommand.java): execute() дописывает текст,
 *       undo() удаляет ровно столько символов, сколько добавил;
 *     - History (файл History.java): run(Command) выполняет и кладёт в стек;
 *       undoLast() снимает со стека и вызывает undo().
 *   В main: добавьте два фрагмента через команды, выведите текст, отмените
 *   последнюю команду, снова выведите.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   После двух добавлений: 'Привет, мир'
 *   После undo: 'Привет'
 *
 * ТРЕБОВАНИЯ:
 *   - undo() точно обращает execute() (удаляет добавленные символы);
 *   - History хранит историю команд (стек) для отмены;
 *   - команда инкапсулирует и действие, и его откат.
 *
 * ПОДСКАЗКА:
 *   В AddTextCommand сохраните добавляемый текст, чтобы undo() знал его длину:
 *   doc.deleteLast(text.length()).
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: через History выполните два AddTextCommand, выведите content(),
        //       undoLast(), снова выведите
    }
}
