package com.senai.demo.dtos;

import com.senai.demo.models.entities.Motorista;
import com.senai.demo.models.enums.TipoResiduo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class CaminhaoRequestDTO {

    @NotBlank(message = "Placa é obrigatoria")
    @Pattern(
            regexp = "^[A-Z]{3}\\d[A-Z]\\d{2}$",
            message = "Placa inválida. Use o formato Mercosul: AAA1A11"
    )
    private String placa;

    private Long motorista_id;

    private Double capacidade;

    @NotNull(message = "Residuo é obrigatorio")
    private List<TipoResiduo> tiposResiduos;

    public CaminhaoRequestDTO(String placa, Long motorista_id, Double capacidade, List<TipoResiduo> tiposResiduos) {
        this.placa = placa;
        this.motorista_id = motorista_id;
        this.capacidade = capacidade;
        this.tiposResiduos = tiposResiduos;
    }

    public CaminhaoRequestDTO() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getMotorista_id() {
        return motorista_id;
    }

    public void setMotorista_id(Long motorista_id) {
        this.motorista_id = motorista_id;
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
}
