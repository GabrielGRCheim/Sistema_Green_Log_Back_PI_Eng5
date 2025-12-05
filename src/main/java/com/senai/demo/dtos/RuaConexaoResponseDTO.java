package com.senai.demo.dtos;

import com.senai.demo.models.entities.RuaConexao;

public class RuaConexaoResponseDTO {

    private Long id;
    private String nome;
    private Long origemId;
    private Long destinoId;
    private Float distancia;

    public RuaConexaoResponseDTO(Long id, Long origemId, String nome, Long destinoId, Float distancia) {
        this.id = id;
        this.nome = nome;
        this.origemId = origemId;
        this.destinoId = destinoId;
        this.distancia = distancia;
    }

    public RuaConexaoResponseDTO (Long id, Long origemId, Long destinoId, Float distancia ){
        this.id = id;
        this.origemId = origemId;
        this.destinoId = destinoId;
    }

    public RuaConexaoResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
