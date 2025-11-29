package com.senai.demo.dtos;

import java.time.LocalDate;

public class ItinerarioResponseDTO {

    private Long id;
    private CaminhaoResponseDTO caminhao;
    private RotaResponseDTO rota;
    private LocalDate dia;

    public ItinerarioResponseDTO(Long id, CaminhaoResponseDTO caminhao, RotaResponseDTO rota, LocalDate dia) {
        this.id = id;
        this.dia = dia;
        this.caminhao = caminhao;
        this.rota = rota;
    }

    public Long getId() {
        return id;
    }

    public CaminhaoResponseDTO getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(CaminhaoResponseDTO caminhao) {
        this.caminhao = caminhao;
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
}
