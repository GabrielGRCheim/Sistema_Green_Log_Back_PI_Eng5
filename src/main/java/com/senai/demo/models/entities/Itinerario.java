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
    @JoinColumn(name = "Rota_id")
    private Rota rota;

    @Column(name = "Data")
    private LocalDate dia;

    @Column(name = "Ativo")
    private boolean ativo = true;

    public Itinerario(Rota rota, LocalDate dia, boolean ativo) {
        this.rota = rota;
        this.dia = dia;
        this.ativo = ativo;
    }

    public Itinerario() {
    }

    public Long getId() {
        return id;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
