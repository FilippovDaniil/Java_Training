import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

/** Имитация «файлового» хранилища. */
class FileProductRepository implements ProductRepository {
    @Override
    public java.util.List<String> findAll() {
        // В реальности — чтение из файла
        return java.util.List.of("Стол", "Стул", "Лампа");
    }
}
