package com.example.filecomponentsystem.component.interfaces.impl;

import com.example.filecomponentsystem.component.interfaces.FileManagerComponent;
import com.example.filecomponentsystem.component.interfaces.FileOperationComponent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FileManagerComponentImpl implements FileManagerComponent {

    private final Map<String, FileOperationComponent> operations = new HashMap<>();

    // Injection par constructeur de tous les composants FileOperationComponent
    public FileManagerComponentImpl(List<FileOperationComponent> operationComponents) {
        for (FileOperationComponent component : operationComponents) {
            operations.put(component.getCommandName(), component);
            System.out.println("✓ Composant chargé : " + component.getCommandName());
        }
    }

    @Override
    public boolean executeOperation(String commandName, String... args) {
        FileOperationComponent operation = operations.get(commandName.toLowerCase());
        if (operation == null) {
            System.err.println("Erreur : Commande inconnue - " + commandName);
            return false;
        }

        try {
            return operation.execute(args);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la commande : " + e.getMessage());
            return false;
        }
    }

    @Override
    public void listAvailableOperations() {
        System.out.println("\n=== Commandes disponibles ===");
        operations.values().forEach(component -> {
            System.out.println("• " + component.getUsage());
        });
        System.out.println("• help - Affiche cette aide");
        System.out.println("• exit - Quitte l'application");
        System.out.println("=============================\n");
    }

    @Override
    public boolean hasOperation(String commandName) {
        return operations.containsKey(commandName.toLowerCase());
    }
}