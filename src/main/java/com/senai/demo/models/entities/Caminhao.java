package com.senai.demo.models.entities;

import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.models.padraoprojeto.decorator.Residuos;
import com.senai.demo.models.padraoprojeto.factory.ResiduoFactory;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Caminhoes")
public class Caminhao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Placas", nullable = false,unique = true)
    private String placa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Motoristas")
    private Motorista motorista;

    @Column(name = "Capacidades")
    private Double capacidade;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<TipoResiduo> tiposResiduos = new HashSet<>();

    @Column(name = "Ativo")
    private boolean ativo = true;

    @Transient
    private Residuos residuosDecorator;

    @PostLoad
    private void initDecorator() {
        this.residuosDecorator = ResiduoFactory.criar(tiposResiduos.stream().toList());
    }

    public Caminhao(String placa, Motorista motorista_id, Double capacidade, Set<TipoResiduo> tiposResiduos, boolean ativo) {
        this.placa = placa;
        this.motorista = motorista_id;
        this.capacidade = capacidade;
        this.tiposResiduos = tiposResiduos;
        this.ativo = ativo;
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

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public Set<TipoResiduo> getTiposResiduos() {
        return tiposResiduos;
    }

    public Residuos getResiduosDecorator() {
        return residuosDecorator;
    }

    public void setTiposResiduos(Set<TipoResiduo> tipos) {
        this.tiposResiduos = tipos;
        this.residuosDecorator = ResiduoFactory.criar(tipos.stream().toList());
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
