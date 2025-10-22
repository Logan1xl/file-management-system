package com.example.filecomponentsystem.component.interfaces;

/**
 * Interface du gestionnaire de composants d'opérations sur fichiers
 */
public interface FileManagerComponent {
    boolean executeOperation(String commandName, String... args);
    void listAvailableOperations();
    boolean hasOperation(String commandName);
}