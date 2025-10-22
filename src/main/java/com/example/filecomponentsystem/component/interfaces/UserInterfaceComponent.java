package com.example.filecomponentsystem.component.interfaces;

/**
 * Interface pour les composants d'interface utilisateur
 */
public interface UserInterfaceComponent {
    void start();
    boolean processCommand(String input);
    void displayWelcomeMessage();
    void displayGoodbyeMessage();
}