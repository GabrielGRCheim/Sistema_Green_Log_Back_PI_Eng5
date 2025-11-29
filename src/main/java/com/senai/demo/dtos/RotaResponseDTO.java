package com.senai.demo.dtos;

import java.time.LocalDateTime;

public class RotaResponseDTO {

    private Long id;
    private CaminhaoResponseDTO caminhao;
    private String nome;
    private String bairros;
    private String arestas;
    private Float distancia_total;
    private String residuos_atendidos;
    private LocalDateTime dataCriacao;

    public RotaResponseDTO(Long id, CaminhaoResponseDTO caminhao, String nome, String bairros,
                           String arestas, Float distancia_total, String residuos_atendidos,
                           LocalDateTime dataCriacao) {

        this.id = id;
        this.caminhao = caminhao;
        this.nome = nome;
        this.bairros = bairros;
        this.arestas = arestas;
        this.distancia_total = distancia_total;
        this.residuos_atendidos = residuos_atendidos;
        this.dataCriacao = dataCriacao;
    }

    public RotaResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CaminhaoResponseDTO getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(CaminhaoResponseDTO caminhao) {
        this.caminhao = caminhao;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
