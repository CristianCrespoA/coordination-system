package com.coordination.system.controller;

import com.coordination.system.entity.Pilar;
import com.coordination.system.service.PilarService;
import com.coordination.system.dto.UpdatePosRequest;
import com.coordination.system.dto.UpdatePosResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilares")
public class PilarController {

    private final PilarService service;

    public PilarController(PilarService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pilar> getPilar(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/actualizar-posicion")
    public ResponseEntity<?> actualizarPosicion(@RequestBody UpdatePosRequest req) {
        Pilar actualizado = service.saveOrUpdatePosition(req.getPilarId(), req.getPosX(), req.getPosY(), req.getEstado());
        return ResponseEntity.status(201).body(new UpdatePosResponse("Posición actualizada exitosamente.", actualizado));
    }
    
    @GetMapping
    public ResponseEntity<List<Pilar>> getAllPilares() {
        return ResponseEntity.ok(service.findAll());
    }
}