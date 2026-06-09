interface FileVisitor {
    void visitFile(FileItem f);
    void visitDir(DirItem d);
}
