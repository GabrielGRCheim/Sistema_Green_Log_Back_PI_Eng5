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
        rua.setNome(dto.getNome());
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
                rua.getNome(),
                rua.getDestinoId(),
                rua.getDistancia()
        );
    }

    // Atualizar entidade existente a partir do DTO
    public static void updateEntity(RuaConexao rua, RuaConexaoRequestDTO dto) {
        if (rua == null || dto == null) return;
        if(dto.getOrigemId() != null){
            rua.setOrigemId(dto.getOrigemId());
        }
        if(dto.getNome() != null){
            rua.setNome(dto.getNome());
        }
        if(dto.getDestinoId() != null){
            rua.setDestinoId(dto.getDestinoId());
        }
        if(dto.getDistancia() != null){
            rua.setDistancia(dto.getDistancia());
        }
    }

    // Lista de entidades → Lista de DTO
    public static List<RuaConexaoResponseDTO> toDTOList(List<RuaConexao> ruas) {
        if (ruas == null) return null;

        return ruas.stream()
                .map(RuaConexaoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
