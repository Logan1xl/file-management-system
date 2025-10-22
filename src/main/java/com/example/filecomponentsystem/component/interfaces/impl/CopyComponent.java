package com.example.filecomponentsystem.component.interfaces.impl;

import com.example.filecomponentsystem.component.interfaces.FileOperationComponent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class CopyComponent implements FileOperationComponent {

    @Override
    public boolean execute(String... args) {
        if (!validateArgs(args)) {
            return false;
        }

        try {
            Path source = Paths.get(args[0]);
            Path destination = Paths.get(args[1]);

            if (!Files.exists(source)) {
                System.err.println("Erreur : Le fichier source n'existe pas : " + source);
                return false;
            }

            // Si la destination est un dossier, copie avec le même nom de fichier
            if (Files.isDirectory(destination)) {
                Path fileName = source.getFileName();
                destination = destination.resolve(fileName);
            }

            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✓ Fichier copié avec succès : " + source + " → " + destination);
            return true;

        } catch (IOException e) {
            System.err.println("Erreur lors de la copie : " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getCommandName() {
        return "copy";
    }

    @Override
    public String getUsage() {
        return "copy <source> <destination> - Copie un fichier";
    }

    @Override
    public boolean validateArgs(String... args) {
        if (args == null || args.length != 2) {
            System.err.println("Erreur : La commande copy nécessite 2 arguments");
            System.err.println("Usage : " + getUsage());
            return false;
        }
        return true;
    }
}