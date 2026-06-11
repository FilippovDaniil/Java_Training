package m13_iterator_visitor.practice.task05;

interface FileVisitor {
    void visitFile(FileItem f);
    void visitDir(DirItem d);
}
