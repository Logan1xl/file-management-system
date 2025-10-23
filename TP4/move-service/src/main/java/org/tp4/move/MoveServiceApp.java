package org.tp4.move;

import io.javalin.Javalin;
import org.tp4.move.controller.MoveController;

public class MoveServiceApp {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.showJavalinBanner = false;
        }).start(8082);

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║   MOVE SERVICE - Port 8082            ║");
        System.out.println("║   Endpoints disponibles :             ║");
        System.out.println("║   POST /api/files/move                ║");
        System.out.println("║   GET  /api/files/health              ║");
        System.out.println("╚═══════════════════════════════════════╝");

        MoveController controller = new MoveController();
        controller.registerRoutes(app);
    }
}