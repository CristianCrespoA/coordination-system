package com.coordination.system.service;

import com.coordination.system.entity.Pilar;
import com.coordination.system.exception.ResourceNotFoundException;
import com.coordination.system.repository.PilarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilarService {
    private final PilarRepository repo;

    public PilarService(PilarRepository repo) { this.repo = repo; }

    public Pilar getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pilar no encontrado"));
    }

    public Pilar saveOrUpdatePosition(Long pilarId, Integer posX, Integer posY, String estado) {
        Pilar p = repo.findById(pilarId).orElseThrow(() -> new ResourceNotFoundException("Pilar no existe"));
        p.setPosX(posX);
        p.setPosY(posY);
        p.setEstado(estado);
        return repo.save(p);
    }

    public List<Pilar> findAll() { return repo.findAll(); }
}