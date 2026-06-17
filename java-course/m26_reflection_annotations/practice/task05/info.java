package m26_reflection_annotations.practice.task05;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)   // доступна во время выполнения
@Target(ElementType.TYPE)            // применяется только к классам/интерфейсам
public @interface info {
    String author();                 // обязательный параметр
    String version() default "1.0";  // необязательный параметр со значением по умолчанию
}
