package com.senai.demo.dtos;

import jakarta.validation.constraints.NotBlank;

public class RuaConexaoRequestDTO {

    @NotBlank(message = "Origem é obrigatorio")
    private Long origemId;

    @NotBlank(message = "Destino é obrigatorio")
    private Long destinoId;

    @NotBlank(message = "Distancia é obrigatorio")
    private Float distancia;

    public RuaConexaoRequestDTO() {
    }

    public Long getOrigemId() {
        return origemId;
    }

    public void setOrigemId(Long origemId) {
        this.origemId = origemId;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }
}
