package org.tp4.copy.controller;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.tp4.copy.model.CopyRequest;
import org.tp4.copy.service.FileService;

public class CopyController {

    private final FileService fileService;
    private final Gson gson;

    public CopyController() {
        this.fileService = new FileService();
        this.gson = new Gson();
    }

    public void registerRoutes(Javalin app) {
        // Route pour copier un fichier
        app.post("/api/files/copy", this::copyFile);

        // Route de santé
        app.get("/api/files/health", ctx -> {
            ctx.result("Copy Service is running on port 8081");
        });
    }

    private void copyFile(Context ctx) {
        try {
            CopyRequest request = gson.fromJson(ctx.body(), CopyRequest.class);

            if (request.getSource() == null || request.getDestination() == null) {
                ctx.status(400).result("Erreur : Les champs 'source' et 'destination' sont requis");
                return;
            }

            String result = fileService.copyFile(request.getSource(), request.getDestination());

            if (result.startsWith("Erreur")) {
                ctx.status(400).result(result);
            } else {
                ctx.status(200).result(result);
            }

        } catch (Exception e) {
            ctx.status(500).result("Erreur serveur : " + e.getMessage());
        }
    }
}