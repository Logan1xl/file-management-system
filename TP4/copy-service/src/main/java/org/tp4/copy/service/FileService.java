package org.tp4.copy.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileService {
    public String copyFile(String source, String destination){

        try{
            Path sourcePath = Paths.get(source);
            Path destinationPath = Paths.get(destination);

            if (!Files.exists(sourcePath)){
                return "Erreur : le fichier source n'existe pas :" + source;
            }

            if (Files.isDirectory(destinationPath)) {
                String fileName = sourcePath.getFileName().toString();
                destinationPath = destinationPath.resolve(fileName);
            }

            Files.copy(sourcePath,destinationPath, StandardCopyOption.REPLACE_EXISTING);

            return "Fichier copié avec succès de"+ source + "vers" + destination;

        } catch (IOException e) {
            return "Erreur lors de la copie :"+ e.getMessage();
        }
    }
}
