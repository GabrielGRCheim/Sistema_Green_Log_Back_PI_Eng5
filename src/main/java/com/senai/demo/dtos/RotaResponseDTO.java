package com.senai.demo.dtos;

import com.senai.demo.models.enums.TipoResiduo;

import java.time.LocalDateTime;
import java.util.List;

public class RotaResponseDTO {

    private Long id;
    private CaminhaoResponseDTO caminhao;
    private String nome;
    private List<Long> bairros;
    private List<Long> arestas;
    private Float distancia_total;
    private TipoResiduo tiposResiduos;
    private LocalDateTime dataCriacao;
    private boolean ativo;

    public RotaResponseDTO(Long id, CaminhaoResponseDTO caminhao, String nome, List<Long> bairros,
                           List<Long> arestas, Float distancia_total, TipoResiduo tiposResiduos,
                           LocalDateTime dataCriacao, boolean ativo) {

        this.id = id;
        this.caminhao = caminhao;
        this.nome = nome;
        this.bairros = bairros;
        this.arestas = arestas;
        this.distancia_total = distancia_total;
        this.tiposResiduos = tiposResiduos;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
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

    public List<Long> getBairros() {
        return bairros;
    }

    public void setBairros(List<Long> bairros) {
        this.bairros = bairros;
    }

    public List<Long> getArestas() {
        return arestas;
    }

    public void setArestas(List<Long> arestas) {
        this.arestas = arestas;
    }

    public Float getDistancia_total() {
        return distancia_total;
    }

    public void setDistancia_total(Float distancia_total) {
        this.distancia_total = distancia_total;
    }

    public TipoResiduo getTiposResiduos() {
        return tiposResiduos;
    }

    public void setTiposResiduos(TipoResiduo tiposResiduos) {
        this.tiposResiduos = tiposResiduos;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
