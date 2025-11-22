package com.coordination.system.controller;

import com.coordination.system.entity.Mensaje;
import com.coordination.system.service.MensajeService;
import com.coordination.system.dto.MensajeRequest;
import com.coordination.system.dto.ReconstruirRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService service;

    public MensajeController(MensajeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody MensajeRequest req) {
        Mensaje m = service.createFragment(req.getPilarId(), req.getContenidoFragmentado());
        return ResponseEntity.status(201).body(m);
    }

    @PutMapping("/{id}/reconstruir")
    public ResponseEntity<Mensaje> reconstruir(@PathVariable Long id, @RequestBody ReconstruirRequest req) {
        Mensaje m = service.reconstruir(id, req.getContenidoReconstruido());
        return ResponseEntity.ok(m);
    }
    
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<Mensaje> mensajes = service.findAll();

        if (mensajes.isEmpty()) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "mensaje", "No hay mensajes registrados.",
                            "total", 0
                    )
            );
        }

        return ResponseEntity.ok(mensajes);
    }

}
