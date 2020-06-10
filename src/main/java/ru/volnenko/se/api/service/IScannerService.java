package ru.volnenko.se.api.service;

import java.util.function.Consumer;

/**
 * A service used to synchronize jobs which use the scanner
 * to avoid the scanner being used by different threads at the same time.
 **/
public interface IScannerService {
    
    /**
     * Prints the info message to the console, then waits for a user input and finally
     * runs the specified task using the entered text. The method is synchronized, meaning that no two threads
     * can access it at the same time, so that there are never multiple scanner-using jobs running parallel.
     * 
     * Basically used to make sure, that a command, which uses the scanner, has a chance to complete, before
     * the Bootstrap loop strikes in again and takes over the scanner.
     * 
     * @param infoMessage A text describing, what the user should enter to be printed before starting to wait for input.
     * @param task A task to be executed based on what the user entered
     * @return The entered text
     **/
    String readInputAndDoTask(String infoMessage, Consumer<String> task);

}
