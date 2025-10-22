package com.example.filecomponentsystem.component.interfaces;

/**
 * Interface de base pour toutes les opérations sur les fichiers.
 * Principe de programmation par composants : séparation interface/implémentation
 */
public interface FileOperationComponent {

    /**
     * Exécute l'opération sur le fichier
     * @param args Arguments nécessaires à l'opération
     * @return true si succès, false sinon
     */
    boolean execute(String... args);

    /**
     * Retourne le nom de la commande
     * @return Nom de la commande (ex: "copy", "move", "delete")
     */
    String getCommandName();

    /**
     * Retourne une description de l'usage de la commande
     * @return Description d'utilisation
     */
    String getUsage();

    /**
     * Valide les arguments avant exécution
     * @param args Arguments à valider
     * @return true si arguments valides
     */
    boolean validateArgs(String... args);
}