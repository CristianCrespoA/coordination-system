package com.coordination.system.service;

import com.coordination.system.entity.Mensaje;
import com.coordination.system.exception.ResourceNotFoundException;
import com.coordination.system.repository.MensajeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensajeService {
    private final MensajeRepository repo;

    public MensajeService(MensajeRepository repo) { this.repo = repo; }

    public Mensaje createFragment(Long pilarId, String contenidoFragmentado) {
        Mensaje m = new Mensaje(pilarId, contenidoFragmentado, LocalDateTime.now());
        return repo.save(m);
    }

    public Mensaje reconstruir(Long id, String contenidoReconstruido) {
        Mensaje m = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mensaje no encontrado"));
        m.setContenidoReconstruido(contenidoReconstruido);
        return repo.save(m);
    }
    
    public List<Mensaje> findAll() {
        return repo.findAll();
    }

}