package com.senai.demo.dtos;

import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Itinerario;
import com.senai.demo.models.entities.Rota;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ItinerarioRequestDTO {

    @NotNull(message = "Caminhão é obrigatório")
    private Long caminhaoId;

    @NotNull(message = "Rota é obrigatória")
    private Long rotaId;

    @NotNull(message = "Data é obrigatória")
    private LocalDate dia;

    public ItinerarioRequestDTO() {}

    public Long getCaminhaoId() {
        return caminhaoId;
    }

    public void setCaminhaoId(Long caminhaoId) {
        this.caminhaoId = caminhaoId;
    }

    public Long getRotaId() {
        return rotaId;
    }

    public void setRotaId(Long rotaId) {
        this.rotaId = rotaId;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }
}
