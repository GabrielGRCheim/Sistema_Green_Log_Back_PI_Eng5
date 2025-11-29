package com.senai.demo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Bairros")
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    public Bairro(String nome) {
        this.nome = nome;

    }
    public Bairro() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
