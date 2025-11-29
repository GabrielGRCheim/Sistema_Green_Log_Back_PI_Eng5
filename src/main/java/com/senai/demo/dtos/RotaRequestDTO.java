package com.senai.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RotaRequestDTO {

    @NotNull(message = "O ID do caminhão é obrigatório")
    private Long caminhaoId;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String bairros;

    private String arestas;

    private Float distancia_total;

    @NotBlank(message = "Resíduos atendidos é obrigatório")
    private String residuos_atendidos;

    public RotaRequestDTO() {
    }

    public Long getCaminhaoId() {
        return caminhaoId;
    }

    public void setCaminhaoId(Long caminhaoId) {
        this.caminhaoId = caminhaoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairros() {
        return bairros;
    }

    public void setBairros(String bairros) {
        this.bairros = bairros;
    }

    public String getArestas() {
        return arestas;
    }

    public void setArestas(String arestas) {
        this.arestas = arestas;
    }

    public Float getDistancia_total() {
        return distancia_total;
    }

    public void setDistancia_total(Float distancia_total) {
        this.distancia_total = distancia_total;
    }

    public String getResiduos_atendidos() {
        return residuos_atendidos;
    }

    public void setResiduos_atendidos(String residuos_atendidos) {
        this.residuos_atendidos = residuos_atendidos;
    }
}
