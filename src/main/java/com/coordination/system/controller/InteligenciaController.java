package com.coordination.system.controller;

import com.coordination.system.entity.Pilar;
import com.coordination.system.service.PilarService;
import com.coordination.system.util.TriangulacionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inteligencia")
public class InteligenciaController {
    private final PilarService pilarService;
    public InteligenciaController(PilarService pilarService) { this.pilarService = pilarService; }

    @GetMapping("/triangulacion")
    public ResponseEntity<Map<String,Object>> triangulacion() {
        List<Pilar> pilares = pilarService.findAll();
        Map<String,Object> resp = TriangulacionUtil.estimate(pilares);
        return ResponseEntity.ok(resp);
    }
}