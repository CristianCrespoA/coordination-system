package com.coordination.system.util;

import com.coordination.system.entity.Pilar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriangulacionUtil {

    public static Map<String,Object> estimate(List<Pilar> pilares) {
        Map<String,Object> r = new HashMap<>();

        if (pilares == null || pilares.isEmpty()) {
            r.put("posiblePosicionMuzan", Map.of("x", 0, "y", 0));
            r.put("nivelConfianza", 0.0);
            r.put("descripcion", "No hay suficientes informes de los pilares.");
            return r;
        }

        double sumX = 0;
        double sumY = 0;

        for (Pilar p : pilares) {
            sumX += p.getPosX();
            sumY += p.getPosY();
        }

        double avgX = sumX / pilares.size();
        double avgY = sumY / pilares.size();

        double totalDist = 0;

        for (Pilar p : pilares) {
            double dx = p.getPosX() - avgX;
            double dy = p.getPosY() - avgY;
            totalDist += Math.sqrt(dx*dx + dy*dy);
        }

        double dispersion = totalDist / pilares.size();

        double confianza = 1.0 - Math.min(dispersion / 1000.0, 0.9);
        confianza = Math.round(confianza * 100) / 100.0;

        r.put("posiblePosicionMuzan", Map.of(
                "x", (int)Math.round(avgX),
                "y", (int)Math.round(avgY)
        ));
        r.put("nivelConfianza", confianza);
        r.put("descripcion",
                confianza > 0.6
                        ? "Alta probabilidad de presencia demoníaca cerca del punto calculado."
                        : "Las señales de los pilares son inconsistentes, posible actividad demoníaca dispersa."
        );

        return r;
    }
}
