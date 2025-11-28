package com.senai.demo.mappers;

import com.senai.demo.dtos.BairroResponseDTO;
import com.senai.demo.dtos.PontoColetaRequestDTO;
import com.senai.demo.dtos.PontoColetaResponseDTO;
import com.senai.demo.models.entities.Bairro;
import com.senai.demo.models.entities.PontoColeta;

import java.util.List;
import java.util.stream.Collectors;

public class PontoColetaMapper {

    // RequestDTO → Entity (sem buscar o Bairro)
    public static PontoColeta toEntity(PontoColetaRequestDTO dto) {
        if (dto == null) return null;

        PontoColeta ponto = new PontoColeta();
        ponto.setNome(dto.getNome());
        ponto.setResponsavel(dto.getResponsavel());
        ponto.setTelefoneResponsavel(dto.getTelefoneResponsavel());
        ponto.setEmailResponsavel(dto.getEmailResponsavel());
        ponto.setEndereco(dto.getEndereco());
        ponto.setTiposResiduoAceitos(dto.getTiposResiduoAceitos());

        // Bairro será setado no SERVICE após buscar por ID
        return ponto;
    }

    // Entity → ResponseDTO
    public static PontoColetaResponseDTO toDTO(PontoColeta ponto) {
        if (ponto == null) return null;
        BairroResponseDTO BairroDTO = null;
        Bairro bairro = ponto.getBairro();
        BairroDTO = new BairroResponseDTO(
                bairro.getId(),
                bairro.getNome()
        );

        return new PontoColetaResponseDTO(
                ponto.getId(),
                BairroDTO,
                ponto.getNome(),
                ponto.getResponsavel(),
                ponto.getTelefoneResponsavel(),
                ponto.getEmailResponsavel(),
                ponto.getEndereco(),
                ponto.getTiposResiduoAceitos()
        );
    }

    // Atualizar entidade existente a partir do DTO
    public static void updateEntity(PontoColeta entity, PontoColetaRequestDTO dto) {
        if (entity == null || dto == null) return;

        entity.setNome(dto.getNome());
        entity.setResponsavel(dto.getResponsavel());
        entity.setTelefoneResponsavel(dto.getTelefoneResponsavel());
        entity.setEmailResponsavel(dto.getEmailResponsavel());
        entity.setEndereco(dto.getEndereco());
        entity.setTiposResiduoAceitos(dto.getTiposResiduoAceitos());

        // Bairro também será atualizado no SERVICE
    }

    // Lista de entidades -> Lista de DTO
    public static List<PontoColetaResponseDTO> toDTOList(List<PontoColeta> lista) {
        if (lista == null) return null;

        return lista.stream()
                .map(PontoColetaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
