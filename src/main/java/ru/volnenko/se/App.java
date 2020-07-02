package ru.volnenko.se;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.volnenko.se.listener.AbstractEventListener;
import ru.volnenko.se.controller.Bootstrap;

public class App {

    private static Set<AbstractEventListener> commands;

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        final Bootstrap bootstrap = context.getBean(Bootstrap.class);
        commands = new HashSet<>(context.getBeansOfType(AbstractEventListener.class).values());
        bootstrap.init(commands);
    }

}
