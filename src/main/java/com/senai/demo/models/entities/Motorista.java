package com.senai.demo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Motoristas")
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "Nomes")
    private String nome;

    @NotNull
    @Column(name = "Cpf")
    private String CPF;

    @NotNull
    @Column(name = "Ativo")
    private boolean ativo = true;

    public Motorista() {
    }

    public Motorista(String nome, String CPF, boolean ativo) {
        this.nome = nome;
        this.CPF = CPF;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
