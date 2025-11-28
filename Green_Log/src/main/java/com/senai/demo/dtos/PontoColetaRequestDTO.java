package com.senai.demo.dtos;

import com.senai.demo.models.entities.Bairro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PontoColetaRequestDTO {

    @NotNull(message = "Bairro é obrigatorio")
    private Long bairroId;

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    private String responsavel;
    private String telefoneResponsavel;
    private String emailResponsavel;
    private String endereco;
    private String horarioFuncionamento;

    @NotBlank(message = "Resíduos são obrigatorios")
    private String tiposResiduoAceitos;

    public PontoColetaRequestDTO() {
    }

    public Long getBairroId() {
        return bairroId;
    }

    public void setBairroId(Long bairroId) {
        this.bairroId = bairroId;
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

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public String getTiposResiduoAceitos() {
        return tiposResiduoAceitos;
    }

    public void setTiposResiduoAceitos(String tiposResiduoAceitos) {
        this.tiposResiduoAceitos = tiposResiduoAceitos;
    }
}
