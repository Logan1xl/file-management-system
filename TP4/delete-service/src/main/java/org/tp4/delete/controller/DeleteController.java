package org.tp4.delete.controller;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.tp4.delete.model.DeleteRequest;
import org.tp4.delete.service.FileService;

public class DeleteController {

    private final FileService fileService;
    private final Gson gson;

    public DeleteController() {
        this.fileService = new FileService();
        this.gson = new Gson();
    }

    public void registerRoutes(Javalin app) {
        // Accepte DELETE avec le path en query param
        app.delete("/api/files/delete", this::deleteFile);

        app.get("/api/files/health", ctx -> {
            ctx.result("Delete Service is running on port 8083");
        });
    }

    private void deleteFile(Context ctx) {
        try {
            String path = ctx.queryParam("path");

            if (path == null || path.isEmpty()) {
                ctx.status(400).result("Erreur : Le paramètre 'path' est requis");
                return;
            }

            String result = fileService.deleteFile(path);

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