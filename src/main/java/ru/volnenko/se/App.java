package ru.volnenko.se;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.controller.Bootstrap;

@Configuration
@ComponentScan
@EnableAsync
public class App {

    private static Set<AbstractEventListener> commands;

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        
        final Bootstrap bootstrap = context.getBean(Bootstrap.class);
        commands = new HashSet<>(context.getBeansOfType(AbstractEventListener.class).values());
        bootstrap.init(commands);
    }
    
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
    
    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster eventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster =
                new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor("CommandThread_"));
        return eventMulticaster;
    }

}
