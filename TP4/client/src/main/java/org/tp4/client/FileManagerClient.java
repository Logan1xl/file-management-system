package org.tp4.client;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManagerClient {

    private static final String COPY_SERVICE_URL = "http://localhost:8081/api/files/copy";
    private static final String MOVE_SERVICE_URL = "http://localhost:8082/api/files/move";
    private static final String DELETE_SERVICE_URL = "http://localhost:8083/api/files/delete";

    private final HttpClient httpClient;
    private final Gson gson;

    public FileManagerClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public void copyFile(String source, String destination) {
        try {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("source", source);
            requestBody.put("destination", destination);

            String jsonBody = gson.toJson(requestBody);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(COPY_SERVICE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("✓ " + response.body());

        } catch (IOException | InterruptedException e) {
            System.err.println("✗ Erreur : Service de copie inaccessible (port 8081)");
        }
    }

    public void moveFile(String source, String destination) {
        try {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("source", source);
            requestBody.put("destination", destination);

            String jsonBody = gson.toJson(requestBody);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MOVE_SERVICE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("✓ " + response.body());

        } catch (IOException | InterruptedException e) {
            System.err.println("✗ Erreur : Service de déplacement inaccessible (port 8082)");
        }
    }

    public void deleteFile(String path) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(DELETE_SERVICE_URL + "?path=" + path))
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("✓ " + response.body());

        } catch (IOException | InterruptedException e) {
            System.err.println("✗ Erreur : Service de suppression inaccessible (port 8083)");
        }
    }

    public static void main(String[] args) {
        FileManagerClient client = new FileManagerClient();
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   GESTIONNAIRE DE FICHIERS - ARCHITECTURE SOA    ║");
        System.out.println("║                  (avec Javalin)                   ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Commandes disponibles :");
        System.out.println("  copy <source> <destination>  - Copier un fichier");
        System.out.println("  move <source> <destination>  - Déplacer un fichier");
        System.out.println("  delete <path>                - Supprimer un fichier");
        System.out.println("  exit                         - Quitter");
        System.out.println();

        while (true) {
            System.out.print("filemanager> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split("\\s+");
            String command = parts[0].toLowerCase();

            switch (command) {
                case "copy":
                    if (parts.length != 3) {
                        System.out.println("Usage : copy <source> <destination>");
                    } else {
                        client.copyFile(parts[1], parts[2]);
                    }
                    break;

                case "move":
                    if (parts.length != 3) {
                        System.out.println("Usage : move <source> <destination>");
                    } else {
                        client.moveFile(parts[1], parts[2]);
                    }
                    break;

                case "delete":
                    if (parts.length != 2) {
                        System.out.println("Usage : delete <path>");
                    } else {
                        client.deleteFile(parts[1]);
                    }
                    break;

                case "exit":
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;

                default:
                    System.out.println("Commande inconnue : " + command);
            }
            System.out.println();
        }
    }
}