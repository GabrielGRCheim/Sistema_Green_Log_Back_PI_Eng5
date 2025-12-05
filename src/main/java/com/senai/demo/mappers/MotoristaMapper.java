package com.senai.demo.mappers;

import com.senai.demo.dtos.MotoristaRequestDTO;
import com.senai.demo.dtos.MotoristaResponseDTO;
import com.senai.demo.models.entities.Motorista;

import java.util.List;
import java.util.stream.Collectors;

public class MotoristaMapper {

    // DTO → ENTITY
    public static Motorista toEntity(MotoristaRequestDTO dto) {
        if (dto == null) return null;

        Motorista motorista = new Motorista();
        motorista.setNome(dto.getNome());
        motorista.setCPF(dto.getCpf());;

        return motorista;
    }

    // ENTITY → DTO
    public static MotoristaResponseDTO toDTO(Motorista motorista) {
        if (motorista == null) return null;

        return new MotoristaResponseDTO(
                motorista.getId(),
                motorista.getNome(),
                motorista.getCPF(),
                motorista.isAtivo()
        );
    }

    // UPDATE ENTITY
    public static void updateEntity(Motorista motorista, MotoristaRequestDTO dto) {
        if (motorista == null || dto == null) return;

        if(dto.getNome() != null) {motorista.setNome(dto.getNome());}
        if(dto.getCpf() != null) {motorista.setCPF(dto.getCpf());}
    }

    // LISTA DE ENTITY → LISTA DE DTO
    public static List<MotoristaResponseDTO> toDTOList(List<Motorista> motoristas) {
        if (motoristas == null) return null;

        return motoristas.stream()
                .map(MotoristaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
