package com.example.filecomponentsystem.component.interfaces.impl;

import com.example.filecomponentsystem.component.interfaces.ErrorHandlerComponent;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ErrorHandlerComponentImpl implements ErrorHandlerComponent {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void handleError(Exception e) {
        logError(e.getMessage());
        displayError("Une erreur s'est produite : " + e.getMessage());
    }

    @Override
    public void logError(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.err.println("[" + timestamp + "] ERROR: " + message);
    }

    @Override
    public void displayError(String message) {
        System.err.println("❌ " + message);
    }
}
