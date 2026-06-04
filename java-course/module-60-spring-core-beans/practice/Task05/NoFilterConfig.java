import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// Конфигурация БЕЗ фильтра (пакет для SpamFilter не сканируется)
// TODO: @Configuration + @ComponentScan, не включающий SpamFilter
class NoFilterConfig { }
