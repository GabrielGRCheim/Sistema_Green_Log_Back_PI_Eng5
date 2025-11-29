package com.senai.demo.dtos;

public class BairroRequestDTO {

    private String nome;

    public BairroRequestDTO(String nome) {
        this.nome = nome;
    }

    public BairroRequestDTO() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
