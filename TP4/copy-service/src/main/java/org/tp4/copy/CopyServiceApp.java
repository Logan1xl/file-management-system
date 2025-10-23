package org.tp4.copy;

import io.javalin.Javalin;
import org.tp4.copy.controller.CopyController;

public class CopyServiceApp {

    public static void main(String[] args) {
        // Créer l'application Javalin sur le port 8081
        Javalin app = Javalin.create(config -> {
            config.showJavalinBanner = false;
        }).start(8081);

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║   COPY SERVICE - Port 8081            ║");
        System.out.println("║   Endpoints disponibles :             ║");
        System.out.println("║   POST /api/files/copy                ║");
        System.out.println("║   GET  /api/files/health              ║");
        System.out.println("╚═══════════════════════════════════════╝");

        // Enregistrer les routes
        CopyController controller = new CopyController();
        controller.registerRoutes(app);
    }
}