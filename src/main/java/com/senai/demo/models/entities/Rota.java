package com.senai.demo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Rotas")
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caminhao_id")
    private Caminhao caminhaoDesignado;

    @Column(name = "Nomes", nullable = false)
    private String nome;

    @Column(name = "Bairros", nullable = false)
    private String bairros;

    @Column(name = "Arestas", nullable = false)
    private String arestas;

    @Column(name = "Distancia_Total", nullable = false)
    private Float distancia_total;

    @Column(name = "Residuos_Atendidos", nullable = false)
    private String residuos_atendidos;

    @Column(name = "Data_Criacao", updatable = false)
    private LocalDateTime dataCriacao =  LocalDateTime.now().minusNanos(0);

    public Rota(Float distancia_total, String residuos_atendidos, String arestas, String bairros, String nome, Caminhao caminhaoDesignado) {
        this.distancia_total = distancia_total;
        this.residuos_atendidos = residuos_atendidos;
        this.arestas = arestas;
        this.bairros = bairros;
        this.nome = nome;
        this.caminhaoDesignado = caminhaoDesignado;
    }

    public Rota() {}

    public Long getId() {
        return id;
    }

    public Caminhao getCaminhaoDesignado() {
        return caminhaoDesignado;
    }

    public void setCaminhaoDesignado(Caminhao caminhaoDesignado) {
        this.caminhaoDesignado = caminhaoDesignado;
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
}
