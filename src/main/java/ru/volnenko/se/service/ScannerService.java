package ru.volnenko.se.service;

import java.util.Scanner;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volnenko.se.api.service.IScannerService;

@Service
public class ScannerService implements IScannerService {

    private final Scanner scanner;

    @Autowired
    public ScannerService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public synchronized String readInputAndDoTask(String infoMessage, Consumer<String> task) {
        System.out.println(infoMessage);
        String text = scanner.nextLine();
        task.accept(text);
        return text;
    }

}
