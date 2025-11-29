package com.senai.demo.mappers;

import com.senai.demo.dtos.CaminhaoRequestDTO;
import com.senai.demo.dtos.CaminhaoResponseDTO;
import com.senai.demo.models.entities.Caminhao;

import java.util.List;
import java.util.stream.Collectors;

public class CaminhaoMapper {

    // ENTIDADE -> DTO
    public static CaminhaoResponseDTO toDTO(Caminhao caminhao) {
        if (caminhao == null) return null;

        return new CaminhaoResponseDTO(
                caminhao.getId(),
                caminhao.getPlaca(),
                caminhao.getNomeResponsavel(),
                caminhao.getCapacidade(),
                caminhao.getResiduo()
        );
    }

    // DTO -> ENTIDADE NOVA
    public static Caminhao toEntity(CaminhaoRequestDTO dto) {
        if (dto == null) return null;

        Caminhao caminhao = new Caminhao();
        caminhao.setPlaca(dto.getPlaca());
        caminhao.setNomeResponsavel(dto.getNomeResponsavel());
        caminhao.setCapacidade(dto.getCapacidade());
        caminhao.setResiduo(dto.getResiduo());
        return caminhao;
    }

    // ATUALIZA ENTIDADE EXISTENTE A PARTIR DO DTO
    public static void updateEntity(Caminhao caminhao, CaminhaoRequestDTO dto) {
        if (dto == null || caminhao == null) return;

        caminhao.setPlaca(dto.getPlaca());
        caminhao.setNomeResponsavel(dto.getNomeResponsavel());
        caminhao.setCapacidade(dto.getCapacidade());
        caminhao.setResiduo(dto.getResiduo());
    }

    // LISTA DE ENTIDADES -> LISTA DE DTOs
    public static List<CaminhaoResponseDTO> toDTOList(List<Caminhao> caminhoes) {
        if (caminhoes == null) return null;

        return caminhoes.stream()
                .map(CaminhaoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
