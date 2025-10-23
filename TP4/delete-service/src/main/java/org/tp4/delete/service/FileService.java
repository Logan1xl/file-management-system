package org.tp4.delete.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    public String deleteFile(String path) {
        try {
            Path filePath = Paths.get(path);

            if (!Files.exists(filePath)) {
                return "Erreur : Le fichier n'existe pas : " + path;
            }

            Files.delete(filePath);
            return "Fichier supprimé avec succès : " + path;

        } catch (IOException e) {
            return "Erreur lors de la suppression : " + e.getMessage();
        }
    }
}