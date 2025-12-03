package com.senai.demo.mappers;

import com.senai.demo.dtos.CaminhaoRequestDTO;
import com.senai.demo.dtos.CaminhaoResponseDTO;
import com.senai.demo.dtos.MotoristaResponseDTO;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Motorista;
import com.senai.demo.models.enums.TipoResiduo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CaminhaoMapper {

    // MAPEAMENTO DTO → ENTITY //

    public static Caminhao toEntity(CaminhaoRequestDTO dto) {
        if (dto == null) return null;

        Caminhao caminhao = new Caminhao();
        caminhao.setPlaca(dto.getPlaca());
        caminhao.setCapacidade(dto.getCapacidade());
        caminhao.setTiposResiduos(new HashSet<>(dto.getTiposResiduos()));


        return caminhao;
    }

    // MAPEAMENTO ENTITY → DTO //

    public static CaminhaoResponseDTO toDTO(Caminhao caminhao) {
        if (caminhao == null) return null;

        MotoristaResponseDTO motoristaDTO = null;

        if(caminhao.getMotorista() != null){
            Motorista motorista = caminhao.getMotorista();
            motoristaDTO = new MotoristaResponseDTO(
                    motorista.getId(),
                    motorista.getNome(),
                    motorista.getCPF(),
                    motorista.isAtivo()
            );

        };

        return new CaminhaoResponseDTO(
                caminhao.getId(),
                caminhao.getPlaca(),
                motoristaDTO,
                caminhao.getCapacidade(),
                caminhao.getTiposResiduos().stream().toList(),
                caminhao.isAtivo()
        );
    }

    // UPDATE DE ENTITY         //

    public static void updateEntity(Caminhao caminhao, CaminhaoRequestDTO dto) {
        if (dto == null || caminhao == null) return;

        if(dto.getPlaca() != null) {caminhao.setPlaca(dto.getPlaca());}
        if(dto.getCapacidade() != null) {caminhao.setCapacidade(dto.getCapacidade());}
        if(dto.getTiposResiduos() != null) {caminhao.setTiposResiduos(new  HashSet<>(dto.getTiposResiduos()));}
    }

    // LISTA → DTO LIST         //

    public static List<CaminhaoResponseDTO> toDTOList(List<Caminhao> caminhoes) {
        if (caminhoes == null) return null;

        return caminhoes.stream()
                .map(CaminhaoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
