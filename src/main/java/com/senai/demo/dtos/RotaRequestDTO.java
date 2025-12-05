package com.senai.demo.dtos;

import com.senai.demo.models.enums.TipoResiduo;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class RotaRequestDTO {

    @NotNull(message = "O ID do caminhão é obrigatório")
    private Long caminhaoId;

    @NotNull(message = "O ID do caminhão é obrigatório")
    private String nome;

    @NotNull(message = "A lista de bairros é obrigatória")
    private List<Long> bairros;

    @NotNull(message = "A lista de arestas é obrigatória")
    private List<Long> arestas;

    @NotNull(message = "O Tipo de Residuo e obrigatório")
    private TipoResiduo tipoResiduo;

    public RotaRequestDTO() {
    }

    public Long getCaminhaoId() {
        return caminhaoId;
    }

    public void setCaminhaoId(Long caminhaoId) {
        this.caminhaoId = caminhaoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Long> getBairros() {
        return bairros;
    }

    public void setBairros(List<Long> bairros) {
        this.bairros = bairros;
    }

    public List<Long> getArestas() {
        return arestas;
    }

    public void setArestas(List<Long> arestas) {
        this.arestas = arestas;
    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }
}
