package com.coordination.system.dto;

import com.coordination.system.entity.Pilar;

public class UpdatePosResponse {

    private String mensaje;
    private Pilar pilar;

    public UpdatePosResponse(String mensaje, Pilar pilar) {
        this.mensaje = mensaje;
        this.pilar = pilar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Pilar getPilar() {
        return pilar;
    }
}
