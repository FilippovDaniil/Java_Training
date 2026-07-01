package m60_spring_core_beans.practice.task05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// Конфигурация С фильтром (SpamFilter зарегистрирован через @ComponentScan)
// TODO: @Configuration + @ComponentScan, включающий пакет где лежит
@ComponentScan(basePackages = "m60_spring_core_beans.practice.task05")
@Configuration
class WithFilterConfig { }
