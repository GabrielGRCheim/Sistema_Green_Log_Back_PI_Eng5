package com.senai.demo.dtos;

import jakarta.validation.constraints.NotBlank;

public class CaminhaoRequestDTO {

    @NotBlank(message = "Placa é obrigatoria")
    private String placa;

    private String nomeResponsavel;

    private Double capacidade;

    @NotBlank(message = "Residuo é obrigatorio")
    private String residuo;

    public CaminhaoRequestDTO(String placa, String nomeResponsavel, Double capacidade, String residuo) {
        this.placa = placa;
        this.nomeResponsavel = nomeResponsavel;
        this.capacidade = capacidade;
        this.residuo = residuo;
    }

    public CaminhaoRequestDTO() {
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
