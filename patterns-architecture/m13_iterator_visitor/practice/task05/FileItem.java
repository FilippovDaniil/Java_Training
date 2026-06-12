package m13_iterator_visitor.practice.task05;

class FileItem implements FileNode {
    final String name;
    final long size;

    FileItem(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void accept(FileVisitor v) {
        // TODO: v.visitFile(this)
    }
}
