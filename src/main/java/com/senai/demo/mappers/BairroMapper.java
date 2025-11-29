package com.senai.demo.mappers;

import com.senai.demo.dtos.BairroRequestDTO;
import com.senai.demo.dtos.BairroResponseDTO;
import com.senai.demo.models.entities.Bairro;

import java.util.List;
import java.util.stream.Collectors;

public class BairroMapper {

    // ENTIDADE -> RESPONSE DTO
    public static BairroResponseDTO toDTO(Bairro bairro) {
        if (bairro == null) return null;

        return new BairroResponseDTO(
                bairro.getId(),
                bairro.getNome()
        );
    }

    // REQUEST DTO -> ENTIDADE
    public static Bairro toEntity(BairroRequestDTO dto) {
        if (dto == null) return null;
        Bairro bairro = new Bairro();
        bairro.setNome(dto.getNome());
        return bairro;
    }

    // ATUALIZAR ENTIDADE EXISTENTE A PARTIR DO DTO
    public static void updateEntity(Bairro bairro, BairroRequestDTO dto) {
        if (dto == null || bairro == null) return;
        bairro.setNome(dto.getNome());
    }

    // LISTA DE ENTIDADES -> LISTA DE DTO
    public static List<BairroResponseDTO> toDTOList(List<Bairro> bairros) {
        if (bairros == null) return null;

        return bairros.stream()
                .map(BairroMapper::toDTO)
                .collect(Collectors.toList());
    }
}
