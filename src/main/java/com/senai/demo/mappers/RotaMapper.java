package com.senai.demo.mappers;

import com.senai.demo.dtos.CaminhaoResponseDTO;
import com.senai.demo.dtos.RotaRequestDTO;
import com.senai.demo.dtos.RotaResponseDTO;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Rota;

import java.util.List;
import java.util.stream.Collectors;

public class RotaMapper {

    // RequestDTO → Entity (sem buscar caminhão!)
    public static Rota toEntity(RotaRequestDTO dto) {
        if (dto == null) return null;

        Rota rota = new Rota();
        rota.setNome(dto.getNome());
        rota.setBairros(dto.getBairros());
        rota.setArestas(dto.getArestas());
        rota.setDistancia_total(dto.getDistancia_total());
        rota.setResiduos_atendidos(dto.getResiduos_atendidos());

        // Aqui NÃO seta caminhaoDesignado, isso é feito no service após buscar pelo ID

        return rota;
    }

    // Entity → ResponseDTO
    public static RotaResponseDTO toDTO(Rota rota) {
        if (rota == null) return null;

        Long caminhaoId = null;
        CaminhaoResponseDTO caminhaoDTO = null;
        if (rota.getCaminhaoDesignado() != null) {
            Caminhao caminhao = rota.getCaminhaoDesignado();
            caminhaoDTO = new CaminhaoResponseDTO(
                    caminhao.getId(),
                    caminhao.getNomeResponsavel(),
                    caminhao.getPlaca(),
                    caminhao.getCapacidade(),
                    caminhao.getResiduo()
            );
        }

        return new RotaResponseDTO(
                rota.getId(),
                caminhaoDTO,
                rota.getNome(),
                rota.getBairros(),
                rota.getArestas(),
                rota.getDistancia_total(),
                rota.getResiduos_atendidos(),
                rota.getDataCriacao()
        );
    }



    // Atualizar entidade existente a partir do DTO
    public static void updateEntity(Rota rota, RotaRequestDTO dto) {
        if (rota == null || dto == null) return;

        rota.setNome(dto.getNome());
        rota.setBairros(dto.getBairros());
        rota.setArestas(dto.getArestas());
        rota.setDistancia_total(dto.getDistancia_total());
        rota.setResiduos_atendidos(dto.getResiduos_atendidos());

        // caminhaoDesignado também será setado no service após buscar pelo ID
    }

    // Lista de entidades → Lista de DTO
    public static List<RotaResponseDTO> toDTOList(List<Rota> rotas) {
        if (rotas == null) return null;

        return rotas.stream()
                .map(RotaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
