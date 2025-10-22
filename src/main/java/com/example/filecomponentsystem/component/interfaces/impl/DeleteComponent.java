package com.example.filecomponentsystem.component.interfaces.impl;

import com.example.filecomponentsystem.component.interfaces.FileOperationComponent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DeleteComponent implements FileOperationComponent {

    @Override
    public boolean execute(String... args) {
        if (!validateArgs(args)) {
            return false;
        }

        try {
            Path path = Paths.get(args[0]);

            if (!Files.exists(path)) {
                System.err.println("Erreur : Le fichier n'existe pas : " + path);
                return false;
            }

            Files.delete(path);
            System.out.println("✓ Fichier supprimé avec succès : " + path);
            return true;

        } catch (IOException e) {
            System.err.println("Erreur lors de la suppression : " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getCommandName() {
        return "delete";
    }

    @Override
    public String getUsage() {
        return "delete <chemin> - Supprime un fichier";
    }

    @Override
    public boolean validateArgs(String... args) {
        if (args == null || args.length != 1) {
            System.err.println("Erreur : La commande delete nécessite 1 argument");
            System.err.println("Usage : " + getUsage());
            return false;
        }
        return true;
    }
}