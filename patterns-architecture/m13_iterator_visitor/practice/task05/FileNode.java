package m13_iterator_visitor.practice.task05;

interface FileNode {
    void accept(FileVisitor v);
}
