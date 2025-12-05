package com.senai.demo.models.entities;

import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.models.padraoprojeto.decorator.Residuos;
import com.senai.demo.models.padraoprojeto.factory.ResiduoFactory;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pontos_Coletas")
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bairro_id")
    private Bairro bairro;

    @Column(name = "Nomes", nullable = false)
    private String nome;

    @Column(name = "Responsaveis")
    private String responsavel;

    @Column(name = "telefone_responsavel")
    private String telefoneResponsavel;

    @Column(name = "email_responsavel")
    private String emailResponsavel;

    @Column(name = "Enderecos")
    private String endereco;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "ponto_residuos",
            joinColumns = @JoinColumn(name = "ponto_id")
    )
    @Column(name = "tipo_residuo")
    private Set<TipoResiduo> tiposResiduos = new HashSet<>();

    @Column(name = "Ativo")
    private boolean ativo = true;

    @Transient
    private Residuos residuosDecorator;

    @PostLoad
    private void initDecorator() {
        this.residuosDecorator = ResiduoFactory.criar(tiposResiduos.stream().toList());
    }

    public PontoColeta(Bairro bairro, String nome, String responsavel, String telefoneResponsavel, String emailResponsavel, String endereco, Set<TipoResiduo> tiposResiduos, boolean ativo) {
        this.bairro = bairro;
        this.nome = nome;
        this.responsavel = responsavel;
        this.telefoneResponsavel = telefoneResponsavel;
        this.emailResponsavel = emailResponsavel;
        this.endereco = endereco;
        this.tiposResiduos = tiposResiduos;
        this.ativo = ativo;
    }

    public PontoColeta() {}

    public Long getId() {
        return id;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<TipoResiduo> getTiposResiduos() {
        return tiposResiduos;
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

