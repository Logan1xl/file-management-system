package com.example.filecomponentsystem.component.interfaces.impl;

import com.example.filecomponentsystem.component.interfaces.FileManagerComponent;
import com.example.filecomponentsystem.component.interfaces.UserInterfaceComponent;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleUIComponent implements UserInterfaceComponent {

    private final FileManagerComponent fileManager;
    private final Scanner scanner;
    private boolean running;

    public ConsoleUIComponent(FileManagerComponent fileManager) {
        this.fileManager = fileManager;
        this.scanner = new Scanner(System.in);
        this.running = false;
    }

    @Override
    public void start() {
        running = true;
        displayWelcomeMessage();

        while (running) {
            System.out.print("file-manager> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            processCommand(input);
        }

        displayGoodbyeMessage();
        scanner.close();
    }

    @Override
    public boolean processCommand(String input) {
        String[] parts = input.split("\\s+");
        String command = parts[0].toLowerCase();

        switch (command) {
            case "exit":
                running = false;
                return true;

            case "help":
                fileManager.listAvailableOperations();
                return true;

            default:
                if (fileManager.hasOperation(command)) {
                    String[] args = new String[parts.length - 1];
                    System.arraycopy(parts, 1, args, 0, parts.length - 1);
                    return fileManager.executeOperation(command, args);
                } else {
                    System.err.println("Commande non reconnue : " + command);
                    System.out.println("Tapez 'help' pour voir les commandes disponibles.");
                    return false;
                }
        }
    }

    @Override
    public void displayWelcomeMessage() {
        System.out.println("====================================");
        System.out.println("  FILE COMPONENT SYSTEM v1.0");
        System.out.println("====================================");
        fileManager.listAvailableOperations();
        System.out.println("Tapez 'help' pour l'aide, 'exit' pour quitter.");
    }

    @Override
    public void displayGoodbyeMessage() {
        System.out.println("\nMerci d'avoir utilisé File Component System. Au revoir ! 👋");
    }
}