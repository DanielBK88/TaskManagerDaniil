package ru.volnenko.se;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.controller.Bootstrap;

@Configuration
@ComponentScan
public class App {

    private static Set<AbstractCommand> commands;

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        
        final Bootstrap bootstrap = context.getBean(Bootstrap.class);
        commands = new HashSet<>(context.getBeansOfType(AbstractCommand.class).values());
        bootstrap.init(commands);
    }
    
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
