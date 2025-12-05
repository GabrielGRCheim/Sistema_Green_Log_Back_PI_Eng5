package com.senai.demo.dtos;

import com.senai.demo.models.entities.Bairro;
import com.senai.demo.models.entities.PontoColeta;
import com.senai.demo.models.enums.TipoResiduo;

import java.util.List;
import java.util.Set;

public class PontoColetaResponseDTO {

    private Long id;
    private BairroResponseDTO bairro;
    private String nome;
    private String responsavel;
    private String telefoneResponsavel;
    private String emailResponsavel;
    private String endereco;
    private Set<TipoResiduo> tiposResiduos;
    private boolean ativo;

    public PontoColetaResponseDTO(Long id, BairroResponseDTO bairro, String nome, String responsavel,
                                  String telefoneResponsavel, String emailResponsavel,
                                  String endereco, Set<TipoResiduo> tiposResiduos, boolean ativo) {
        this.id = id;
        this.bairro = bairro;
        this.nome = nome;
        this.responsavel = responsavel;
        this.telefoneResponsavel = telefoneResponsavel;
        this.emailResponsavel = emailResponsavel;
        this.endereco = endereco;
        this.tiposResiduos = tiposResiduos;
        this.ativo = ativo;
    }

    public PontoColetaResponseDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BairroResponseDTO getBairro() {
        return bairro;
    }

    public void setBairro(BairroResponseDTO bairro) {
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

    public void setTiposResiduos(Set<TipoResiduo> tiposResiduos) {
        this.tiposResiduos = tiposResiduos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
