package com.senai.demo.mappers;

import com.senai.demo.dtos.*;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Itinerario;
import com.senai.demo.models.entities.Motorista;
import com.senai.demo.models.entities.Rota;
import com.senai.demo.models.enums.TipoResiduo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItinerarioMapper {

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

    public static Itinerario toEntity(ItinerarioRequestDTO dto) {
        if (dto == null) return null;

        Itinerario it = new Itinerario();
        it.setDia(dto.getDia());
        return it;
    }

    // MAPEAMENTO ENTITY → DTO //

    public static ItinerarioResponseDTO toDTO(Itinerario it) {
        if (it == null) return null;

        // ----- Rota -----
        CaminhaoResponseDTO caminhaoDTO = null;
        RotaResponseDTO rotaDTO = null;
        MotoristaResponseDTO motoristaDTO = null;
        if (it.getRota() != null) {
            Rota r = it.getRota();
            Caminhao c = r.getCaminhaoDesignado();
            Motorista m = c.getMotorista();

            motoristaDTO = new MotoristaResponseDTO(
                    m.getId(),
                    m.getNome(),
                    m.getCPF(),
                    m.isAtivo()
            );

            caminhaoDTO = new CaminhaoResponseDTO(
                    c.getId(),
                    c.getPlaca(),
                    motoristaDTO,
                    c.getCapacidade(),
                    c.getTiposResiduos().stream().toList(),
                    c.isAtivo()
            );


            rotaDTO = new RotaResponseDTO(
                    r.getId(),
                    caminhaoDTO,
                    r.getNome(),
                    stringToListLong(r.getBairros()),
                    stringToListLong(r.getArestas()),
                    r.getDistancia_total(),
                    r.getTiposResiduos(),
                    r.getDataCriacao(),
                    r.isAtivo()
            );
        }

        return new ItinerarioResponseDTO(
                it.getId(),
                rotaDTO,
                it.getDia(),
                it.isAtivo()
        );
    }

    // UPDATE DE ENTITY         //

    public static void updateEntity(Itinerario entity, ItinerarioRequestDTO dto) {
        if (entity == null || dto == null) return;

        if(dto.getDia() != null) {entity.setDia(dto.getDia());}
        if(dto.getRotaId() != null){entity.setRota(entity.getRota());}
    }

    // LISTA → DTO LIST         //

    public static List<ItinerarioResponseDTO> toDTOList(List<Itinerario> lista) {
        if (lista == null) return null;

        return lista.stream()
                .map(ItinerarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
