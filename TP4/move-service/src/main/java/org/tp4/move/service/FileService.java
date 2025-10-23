package org.tp4.move.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileService {

    public String moveFile(String source, String destination) {
        try {
            Path sourcePath = Paths.get(source);
            Path destinationPath = Paths.get(destination);

            if (!Files.exists(sourcePath)) {
                return "Erreur : Le fichier source n'existe pas : " + source;
            }

            if (Files.isDirectory(destinationPath)) {
                String fileName = sourcePath.getFileName().toString();
                destinationPath = destinationPath.resolve(fileName);
            }

            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            return "Fichier déplacé avec succès de " + source + " vers " + destination;

        } catch (IOException e) {
            return "Erreur lors du déplacement : " + e.getMessage();
        }
    }
}