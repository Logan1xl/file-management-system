package com.example.filecomponentsystem.cli;

import com.example.filecomponentsystem.component.interfaces.UserInterfaceComponent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FileSystemCommandLineRunner implements CommandLineRunner {

    private final UserInterfaceComponent userInterface;

    public FileSystemCommandLineRunner(UserInterfaceComponent userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void run(String... args) throws Exception {
        // Si des arguments sont passés en ligne de commande, les traiter directement
        if (args.length > 0) {
            String command = String.join(" ", args);
            userInterface.processCommand(command);
        } else {
            // Sinon, démarrer l'interface interactive
            userInterface.start();
        }
    }
}