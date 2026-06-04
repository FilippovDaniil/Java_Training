import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Arrays;

// ============================================================
// Конфигурация — нужно включить AOP
// ============================================================

@Configuration
@ComponentScan
// TODO: добавьте @EnableAspectJAutoProxy
class AppConfig03 { }
