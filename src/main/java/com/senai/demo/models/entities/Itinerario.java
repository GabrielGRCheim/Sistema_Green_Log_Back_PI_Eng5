package com.senai.demo.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Itinerarios")
public class Itinerario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Caminhao_id")
    private Caminhao caminhao;

    @ManyToOne
    @JoinColumn(name = "Rota_id")
    private Rota rota;

    private LocalDate dia;

    public Itinerario(Caminhao caminhao, Rota rota, LocalDate dia) {
        this.caminhao = caminhao;
        this.rota = rota;
        this.dia = dia;
    }

    public Itinerario() {
    }

    public Long getId() {
        return id;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }
}
