package com.senai.demo.mappers;

import com.senai.demo.dtos.RuaConexaoRequestDTO;
import com.senai.demo.dtos.RuaConexaoResponseDTO;
import com.senai.demo.models.entities.RuaConexao;

import java.util.List;
import java.util.stream.Collectors;

public class RuaConexaoMapper {

    // RequestDTO → Entity
    public static RuaConexao toEntity(RuaConexaoRequestDTO dto) {
        if (dto == null) return null;

        RuaConexao rua = new RuaConexao();
        rua.setOrigemId(dto.getOrigemId());
        rua.setDestinoId(dto.getDestinoId());
        rua.setDistancia(dto.getDistancia());

        return rua;
    }

    // Entity → ResponseDTO
    public static RuaConexaoResponseDTO toDTO(RuaConexao rua) {
        if (rua == null) return null;

        return new RuaConexaoResponseDTO(
                rua.getId(),
                rua.getOrigemId(),
                rua.getDestinoId(),
                rua.getDistancia()
        );
    }

    // Atualizar entidade existente a partir do DTO
    public static void updateEntity(RuaConexao rua, RuaConexaoRequestDTO dto) {
        if (rua == null || dto == null) return;

        rua.setOrigemId(dto.getOrigemId());
        rua.setDestinoId(dto.getDestinoId());
        rua.setDistancia(dto.getDistancia());
    }

    // Lista de entidades → Lista de DTO
    public static List<RuaConexaoResponseDTO> toDTOList(List<RuaConexao> ruas) {
        if (ruas == null) return null;

        return ruas.stream()
                .map(RuaConexaoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
