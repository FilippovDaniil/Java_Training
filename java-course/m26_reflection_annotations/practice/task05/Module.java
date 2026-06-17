package m26_reflection_annotations.practice.task05;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: объявите аннотацию @Info (RUNTIME, TYPE)

// TODO: пометьте класс Module аннотацией @Info(author=..., version=...)

@info(author = "Ivanov", version = "2.5")
public class Module {

}
