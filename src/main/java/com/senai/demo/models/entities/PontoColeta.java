package com.senai.demo.models.entities;

import jakarta.persistence.*;

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

    @Column(name = "tipos_residuo_aceitos")
    private String tiposResiduoAceitos;

    public PontoColeta(Bairro bairro, String nome, String responsavel, String telefoneResponsavel, String emailResponsavel, String endereco, String tiposResiduoAceitos) {
        this.bairro = bairro;
        this.nome = nome;
        this.responsavel = responsavel;
        this.telefoneResponsavel = telefoneResponsavel;
        this.emailResponsavel = emailResponsavel;
        this.endereco = endereco;
        this.tiposResiduoAceitos = tiposResiduoAceitos;
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

    public String getTiposResiduoAceitos() {
        return tiposResiduoAceitos;
    }

    public void setTiposResiduoAceitos(String tiposResiduoAceitos) {
        this.tiposResiduoAceitos = tiposResiduoAceitos;
    }
}

