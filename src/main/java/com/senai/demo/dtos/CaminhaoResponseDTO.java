package com.senai.demo.dtos;

import com.senai.demo.models.entities.Motorista;
import com.senai.demo.models.enums.TipoResiduo;

import java.util.List;

public class CaminhaoResponseDTO {

    private Long id;
    private String placa;
    private MotoristaResponseDTO motorista;
    private Double capacidade;
    private List<TipoResiduo> tiposResiduos;
    private boolean ativo;

    public CaminhaoResponseDTO(Long id, String placa, MotoristaResponseDTO motorista, Double capacidade, List<TipoResiduo> tiposResiduos, boolean ativo) {
        this.id = id;
        this.placa = placa;
        this.motorista = motorista;
        this.capacidade = capacidade;
        this.tiposResiduos = tiposResiduos;
        this.ativo = ativo;
    }

    public CaminhaoResponseDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public MotoristaResponseDTO getMotorista() {
        return motorista;
    }

    public void setMotorista(MotoristaResponseDTO motorista) {
        this.motorista = motorista;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public List<TipoResiduo> getTiposResiduos() {
        return tiposResiduos;
    }

    public void setTiposResiduos(List<TipoResiduo> tiposResiduos) {
        this.tiposResiduos = tiposResiduos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
