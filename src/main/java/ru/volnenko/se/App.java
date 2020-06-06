package ru.volnenko.se;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.controller.Bootstrap;

public class App {

    private static Set<AbstractCommand> commands;

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        
        final Bootstrap bootstrap = context.getBean(Bootstrap.class);
        commands = (Set<AbstractCommand>) context.getBean("commands");
        bootstrap.init(commands);
    }

}
