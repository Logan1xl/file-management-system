package org.tp4.delete;

import io.javalin.Javalin;
import org.tp4.delete.controller.DeleteController;

public class DeleteServiceApp {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.showJavalinBanner = false;
        }).start(8083);

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║   DELETE SERVICE - Port 8083          ║");
        System.out.println("║   Endpoints disponibles :             ║");
        System.out.println("║   DELETE /api/files/delete?path=...   ║");
        System.out.println("║   GET    /api/files/health            ║");
        System.out.println("╚═══════════════════════════════════════╝");

        DeleteController controller = new DeleteController();
        controller.registerRoutes(app);
    }
}