package com.senai.demo.dtos;

import com.senai.demo.models.entities.Caminhao;

public class CaminhaoResponseDTO {

    private Long id;
    private String placa;
    private String nomeResponsavel;
    private Double capacidade;
    private String residuo;

    public CaminhaoResponseDTO(Long id, String placa, String nomeResponsavel, Double capacidade, String residuo) {
        this.id = id;
        this.placa = placa;
        this.nomeResponsavel = nomeResponsavel;
        this.capacidade = capacidade;
        this.residuo = residuo;
    }

    public CaminhaoResponseDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public String getResiduo() {
        return residuo;
    }

    public void setResiduo(String residuo) {
        this.residuo = residuo;
    }
}
