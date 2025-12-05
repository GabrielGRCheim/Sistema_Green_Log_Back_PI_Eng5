package com.senai.demo.models.entities;

import com.senai.demo.models.enums.TipoResiduo;
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

    @Column(name = "Bairros")
    private String bairros;

    @Column(name = "Arestas")
    private String arestas;

    @Column(name = "Distancia_Total", nullable = false)
    private Float distancia_total;

    @Column(name = "Tipos_Residuos")
    @Enumerated(EnumType.STRING)
    private TipoResiduo tiposResiduos;

    @Column(name = "Ativo")
    private boolean ativo = true;

    @Column(name = "Data_Criacao", updatable = false)
    private LocalDateTime dataCriacao =  LocalDateTime.now().withNano(0);

    public Rota(Float distancia_total, TipoResiduo tiposResiduos, String bairros, String arestas, String nome, Caminhao caminhaoDesignado, boolean ativo) {
        this.distancia_total = distancia_total;
        this.tiposResiduos = tiposResiduos;
        this.arestas = arestas;
        this.bairros = bairros;
        this.nome = nome;
        this.caminhaoDesignado = caminhaoDesignado;
        this.ativo = ativo;
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

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Float getDistancia_total() {
        return distancia_total;
    }

    public void setDistancia_total(Float distancia_total) {
        this.distancia_total = distancia_total;
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

    public TipoResiduo getTiposResiduos() {
        return tiposResiduos;
    }

    public void setTiposResiduos(TipoResiduo tiposResiduos) {
        this.tiposResiduos = tiposResiduos;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
