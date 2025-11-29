package com.senai.demo.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Caminhoes")
public class Caminhao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Placas", nullable = false,unique = true)
    private String placa;

    @Column(name = "Nomes_Motoristas")
    private String nomeResponsavel;

    @Column(name = "Capacidades")
    private Double capacidade;

    @Column(name = "Residuos")
    private String residuo;

    public Caminhao(String placa, String nomeResponsavel, Double capacidade, String residuo) {
        this.placa = placa;
        this.nomeResponsavel = nomeResponsavel;
        this.capacidade = capacidade;
        this.residuo = residuo;
    }

    public Caminhao() {}

    public Long getId() {
        return id;
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
