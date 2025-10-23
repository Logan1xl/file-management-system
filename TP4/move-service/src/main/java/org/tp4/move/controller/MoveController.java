package org.tp4.move.controller;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.tp4.move.model.MoveRequest;
import org.tp4.move.service.FileService;

public class MoveController {

    private final FileService fileService;
    private final Gson gson;

    public MoveController() {
        this.fileService = new FileService();
        this.gson = new Gson();
    }

    public void registerRoutes(Javalin app) {
        app.post("/api/files/move", this::moveFile);
        app.get("/api/files/health", ctx -> {
            ctx.result("Move Service is running on port 8082");
        });
    }

    private void moveFile(Context ctx) {
        try {
            MoveRequest request = gson.fromJson(ctx.body(), MoveRequest.class);

            if (request.getSource() == null || request.getDestination() == null) {
                ctx.status(400).result("Erreur : Les champs 'source' et 'destination' sont requis");
                return;
            }

            String result = fileService.moveFile(request.getSource(), request.getDestination());

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