package com.example.filecomponentsystem.component.interfaces;
/**
 * Interface pour la gestion centralisée des erreurs
 */
public interface ErrorHandlerComponent {
    void handleError(Exception e);
    void logError(String message);
    void displayError(String message);
}