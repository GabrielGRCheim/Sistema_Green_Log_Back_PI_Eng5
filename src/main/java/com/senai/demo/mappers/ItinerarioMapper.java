package com.senai.demo.mappers;

import com.senai.demo.dtos.*;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Itinerario;
import com.senai.demo.models.entities.Rota;

import java.util.List;
import java.util.stream.Collectors;

public class ItinerarioMapper {

    // REQUEST DTO → ENTIDADE (somente IDs, entidades serão buscadas no service)
    public static Itinerario toEntity(ItinerarioRequestDTO dto) {
        if (dto == null) return null;

        Itinerario itinerario = new Itinerario();
        itinerario.setDia(dto.getDia());
        // Caminhão e Rota serão setados no service após buscar no banco
        return itinerario;
    }

    // ENTIDADE → RESPONSE DTO (somente IDs)
    public static ItinerarioResponseDTO toDTO(Itinerario itinerario) {
        if (itinerario == null) return null;
        CaminhaoResponseDTO caminhaoDTO = null;
        if (itinerario.getCaminhao() != null) {
            Caminhao caminhao = itinerario.getCaminhao();
            caminhaoDTO = new CaminhaoResponseDTO(
                    caminhao.getId(),
                    caminhao.getNomeResponsavel(),
                    caminhao.getPlaca(),
                    caminhao.getCapacidade(),
                    caminhao.getResiduo()
            );
        }
        RotaResponseDTO rotaDTO = null;
        if(itinerario.getRota() != null){
            Rota rota = itinerario.getRota();
            rotaDTO = new RotaResponseDTO(
                    rota.getId(),
                    null,
                    rota.getNome(),
                    rota.getBairros(),
                    rota.getArestas(),
                    rota.getDistancia_total(),
                    rota.getResiduos_atendidos(),
                    rota.getDataCriacao()
            );

        }

        return new ItinerarioResponseDTO(
                itinerario.getId(),
                caminhaoDTO,
                rotaDTO,
                itinerario.getDia()
        );
    }

    // Atualiza a entidade a partir do DTO
    public static void updateEntity(Itinerario entity, ItinerarioRequestDTO dto) {
        if (entity == null || dto == null) return;

        entity.setDia(dto.getDia());
        // Caminhão e Rota serão atualizados no service
    }

    // LISTA DE ENTIDADES → LISTA DE DTO
    public static List<ItinerarioResponseDTO> toDTOList(List<Itinerario> lista) {
        if (lista == null) return null;

        return lista.stream()
                .map(ItinerarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
