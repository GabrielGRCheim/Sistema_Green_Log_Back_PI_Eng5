package com.senai.demo.mappers;

import com.senai.demo.dtos.BairroResponseDTO;
import com.senai.demo.dtos.PontoColetaRequestDTO;
import com.senai.demo.dtos.PontoColetaResponseDTO;
import com.senai.demo.models.entities.Bairro;
import com.senai.demo.models.entities.PontoColeta;
import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.models.padraoprojeto.factory.ResiduoFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PontoColetaMapper {

    // CONVERSORES MANUAIS     //

    private static String listEnumToString(List<TipoResiduo> list) {
        if (list == null || list.isEmpty()) return "";
        return list.stream()
                .map(Enum::name)
                .collect(Collectors.joining(";"));
    }

    private static List<TipoResiduo> stringToListEnum(String str) {
        if (str == null || str.isBlank()) return new ArrayList<>();

        return Arrays.stream(str.split(";"))
                .filter(s -> !s.isBlank())
                .map(TipoResiduo::valueOf)
                .collect(Collectors.toList());
    }

    // MAPEAMENTO DTO → ENTITY //

    public static PontoColeta toEntity(PontoColetaRequestDTO dto) {
        if (dto == null) return null;

        PontoColeta ponto = new PontoColeta();
        ponto.setNome(dto.getNome());
        ponto.setResponsavel(dto.getResponsavel());
        ponto.setTelefoneResponsavel(dto.getTelefoneResponsavel());
        ponto.setEmailResponsavel(dto.getEmailResponsavel());
        ponto.setEndereco(dto.getEndereco());
        ponto.setTiposResiduos(new HashSet<>(dto.getTiposResiduos()));


        return ponto;
    }

    // MAPEAMENTO ENTITY → DTO //

    public static PontoColetaResponseDTO toDTO(PontoColeta ponto) {
        if (ponto == null) return null;

        Bairro bairro = ponto.getBairro();
        BairroResponseDTO bairroDTO = new BairroResponseDTO(
                bairro.getId(),
                bairro.getNome()
        );

        return new PontoColetaResponseDTO(
                ponto.getId(),
                bairroDTO,
                ponto.getNome(),
                ponto.getResponsavel(),
                ponto.getTelefoneResponsavel(),
                ponto.getEmailResponsavel(),
                ponto.getEndereco(),
                ponto.getTiposResiduos(),
                ponto.isAtivo()
        );
    }

    // UPDATE DE ENTITY         //

    public static void updateEntity(PontoColeta ponto, PontoColetaRequestDTO dto) {
        if (ponto == null || dto == null) return;

        if (dto.getNome() != null) {ponto.setNome(dto.getNome());}
        if(dto.getResponsavel() != null) {ponto.setResponsavel(dto.getResponsavel());}
        if(dto.getTelefoneResponsavel() != null) {ponto.setTelefoneResponsavel(dto.getTelefoneResponsavel());}
        if(dto.getEmailResponsavel() != null) {ponto.setEmailResponsavel(dto.getEmailResponsavel());}
        if(dto.getEndereco() != null) {ponto.setEndereco(dto.getEndereco());}
        if(dto.getTiposResiduos() != null) {ponto.setTiposResiduos(new  HashSet<>(dto.getTiposResiduos()));}
    }

    // LISTA → DTO LIST         //

    public static List<PontoColetaResponseDTO> toDTOList(List<PontoColeta> lista) {
        if (lista == null) return null;

        return lista.stream()
                .map(PontoColetaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
