package m61_spring_core_advanced.practice.task06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// ============================================================
// ЧАСТЬ A — конфигурация с циклом (вызовет BeanCurrentlyInCreationException)
// ============================================================

@Configuration
@ComponentScan(basePackageClasses = AppConfigCycleA.class)
class AppConfigCycleA {}
