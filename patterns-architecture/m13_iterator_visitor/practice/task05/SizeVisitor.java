package m13_iterator_visitor.practice.task05;

// Операция «суммарный размер» — вынесена из узлов в визитор; рекурсия в visitDir.
class SizeVisitor implements FileVisitor {
    private long total = 0;

    @Override
    public void visitFile(FileItem f) {
        // TODO: total += f.size
    }

    @Override
    public void visitDir(DirItem d) {
        // TODO: для каждого child из d.children() — child.accept(this)
    }

    public long total() {
        return total;
    }
}
