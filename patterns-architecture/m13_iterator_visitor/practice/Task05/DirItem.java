package m13_iterator_visitor.practice.task05;

import java.util.ArrayList;
import java.util.List;

class DirItem implements FileNode {
    final String name;
    private final List<FileNode> children = new ArrayList<>();

    DirItem(String name) {
        this.name = name;
    }

    public void add(FileNode node) {
        // TODO: добавить в children
    }

    public List<FileNode> children() {
        return children;
    }

    @Override
    public void accept(FileVisitor v) {
        // TODO: v.visitDir(this)
    }
}
