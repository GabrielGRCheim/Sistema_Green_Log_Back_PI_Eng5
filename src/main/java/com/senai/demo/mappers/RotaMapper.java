package com.senai.demo.mappers;

import com.senai.demo.dtos.CaminhaoResponseDTO;
import com.senai.demo.dtos.RotaRequestDTO;
import com.senai.demo.dtos.RotaResponseDTO;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Rota;
import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.models.padraoprojeto.factory.ResiduoFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RotaMapper {

    // CONVERSORES MANUAIS     //

    private static String listLongToString(List<Long> list) {
        if (list == null || list.isEmpty()) return "";
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(";"));
    }

    private static List<Long> stringToListLong(String str) {
        if (str == null || str.isBlank()) return new ArrayList<>();

        return Arrays.stream(str.split(";"))
                .filter(s -> !s.isBlank())
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }


    private static List<TipoResiduo> stringToListEnum(String str) {
        if (str == null || str.isBlank()) return new ArrayList<>();

        return Arrays.stream(str.replace(" ", "").toUpperCase().trim().split(","))
                .filter(s -> !s.isBlank())
                .map(TipoResiduo::valueOf)
                .collect(Collectors.toList());
    }

    // MAPEAMENTO DTO → ENTITY //

    public static Rota toEntity(RotaRequestDTO dto) {
        if (dto == null) return null;

        Rota rota = new Rota();

        rota.setBairros(listLongToString(dto.getBairros()));
        rota.setArestas(listLongToString(dto.getArestas()));

        return rota;
    }

    // MAPEAMENTO ENTITY → DTO //

    public static RotaResponseDTO toDTO(Rota rota) {
        if (rota == null) return null;

        CaminhaoResponseDTO caminhaoDTO = null;

        if (rota.getCaminhaoDesignado() != null) {
            Caminhao c = rota.getCaminhaoDesignado();
            caminhaoDTO = new CaminhaoResponseDTO(
                    c.getId(),
                    c.getPlaca(),
                    MotoristaMapper.toDTO(c.getMotorista()),
                    c.getCapacidade(),
                    c.getTiposResiduos().stream().toList(),
                    c.isAtivo()
            );
        }

        return new RotaResponseDTO(
                rota.getId(),
                caminhaoDTO,
                rota.getNome(),
                stringToListLong(rota.getBairros()),
                stringToListLong(rota.getArestas()),
                rota.getDistancia_total(),
                rota.getTiposResiduos(),
                rota.getDataCriacao(),
                rota.isAtivo()
        );
    }

    // UPDATE DE ENTITY         //

    public static void updateEntity(Rota rota, RotaRequestDTO dto) {
        if(rota == null || dto == null) return;

        if(dto.getNome() != null) {rota.setNome(dto.getNome());}
        if(dto.getBairros() != null) {rota.setBairros(listLongToString(dto.getBairros()));}
        if(dto.getArestas() != null) {rota.setArestas(listLongToString(dto.getArestas()));}
    }

    // LISTA → DTO LIST         //

    public static List<RotaResponseDTO> toDTOList(List<Rota> rotas) {
        return rotas.stream().map(RotaMapper::toDTO).collect(Collectors.toList());
    }
}
