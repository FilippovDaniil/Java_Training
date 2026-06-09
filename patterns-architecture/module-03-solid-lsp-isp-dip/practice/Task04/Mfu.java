// Многофункциональное устройство комбинирует все три роли.
class Mfu implements Printer, Scanner, Fax {
    @Override
    public void print(String doc) {
        // TODO: вывести "Печать: " + doc
    }

    @Override
    public void scan(String doc) {
        // TODO: вывести "Сканирование: " + doc
    }

    @Override
    public void fax(String doc) {
        // TODO: вывести "Факс: " + doc
    }
}
