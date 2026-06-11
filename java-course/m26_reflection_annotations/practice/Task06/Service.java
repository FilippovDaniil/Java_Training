package m26_reflection_annotations.practice.task06;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

class Service {
    // TODO: пометьте часть методов аннотацией @Important
    void processPayment() { }
    void log() { }
    void deleteAccount() { }
    void ping() { }
}
