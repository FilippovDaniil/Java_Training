import java.util.ArrayList;
import java.util.List;

// Контейнер (композит): содержит файлы и другие каталоги.
class Directory implements FileSystemNode {
    private final List<FileSystemNode> children = new ArrayList<>();

    public void add(FileSystemNode node) {
        // TODO: добавить узел в children
    }

    @Override
    public long size() {
        // TODO: рекурсивно просуммировать size() всех children
        return 0;
    }
}
