package com.senai.demo.dtos;

import java.time.LocalDate;

public class ItinerarioResponseDTO {

    private Long id;
    private RotaResponseDTO rota;
    private LocalDate dia;
    private boolean ativo;

    public ItinerarioResponseDTO(Long id, RotaResponseDTO rota, LocalDate dia,  boolean ativo) {
        this.id = id;
        this.dia = dia;
        this.rota = rota;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public RotaResponseDTO getRota() {
        return rota;
    }

    public void setRota(RotaResponseDTO rota) {
        this.rota = rota;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
