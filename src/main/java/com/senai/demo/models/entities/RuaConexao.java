package com.senai.demo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Ruas_Conexoes")
public class RuaConexao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Bairros_Origens_Id", nullable = false)
    private Long origemId;

    @Column(name = "Bairros_Destinos_id",nullable = false)
    private Long destinoId;

    @Column(name = "Distancias_km",nullable = false)
    private Float distancia;

    public RuaConexao(Long origemId, Long destinoId, Float distancia) {
        this.origemId = origemId;
        this.destinoId = destinoId;
        this.distancia = distancia;
    }

    public RuaConexao() {}

    public Long getId() {
        return id;
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
