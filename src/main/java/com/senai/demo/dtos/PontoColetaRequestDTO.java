package com.senai.demo.dtos;

import com.senai.demo.models.enums.TipoResiduo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class PontoColetaRequestDTO {

    @NotNull(message = "Bairro é obrigatorio")
    private Long bairroId;

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    private String responsavel;

    @NotBlank(message = "O telefone do responsável é obrigatório")
    @Pattern(
            regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}",
            message = "Digite um telefone válido, ex: (11) 98765-4321"
    )
    private String telefoneResponsavel;

    @Email(message = "Digite um e-mail válido")
    @NotBlank(message = "O e-mail do responsável é obrigatório")
    private String emailResponsavel;

    private String endereco;

    private List<TipoResiduo> tiposResiduos;

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

    public List<TipoResiduo> getTiposResiduos() {
        return tiposResiduos;
    }

    public void setTiposResiduos(List<TipoResiduo> tiposResiduos) {
        this.tiposResiduos = tiposResiduos;
    }

}
