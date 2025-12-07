package com.senai.demo.services;

import com.senai.demo.dtos.ItinerarioRequestDTO;
import com.senai.demo.dtos.ItinerarioResponseDTO;
import com.senai.demo.dtos.MotoristaResponseDTO;
import com.senai.demo.mappers.ItinerarioMapper;
import com.senai.demo.mappers.MotoristaMapper;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Itinerario;
import com.senai.demo.models.entities.Rota;
import com.senai.demo.models.exceptions.BadRequestException;
import com.senai.demo.models.exceptions.NotFoundException;
import com.senai.demo.models.padraoprojeto.singleton.LogEventoSingleton;
import com.senai.demo.models.repositorys.CaminhaoRepository;
import com.senai.demo.models.repositorys.ItinerarioRepository;
import com.senai.demo.models.repositorys.RotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItinerarioService {

    private final ItinerarioRepository itinerarioRepository;
    private final CaminhaoRepository caminhaoRepository;
    private final RotaRepository rotaRepository;

    public ItinerarioService(ItinerarioRepository itinerarioRepository,
                             CaminhaoRepository caminhaoRepository,
                             RotaRepository rotaRepository) {
        this.itinerarioRepository = itinerarioRepository;
        this.caminhaoRepository = caminhaoRepository;
        this.rotaRepository = rotaRepository;
    }

    public ItinerarioResponseDTO criarItinerario(ItinerarioRequestDTO dto) {

        Rota rota = rotaRepository.findById(dto.getRotaId())
                .orElseThrow(() -> new NotFoundException("Rota não encontrada"));

        Caminhao caminhao = caminhaoRepository.findById(rota.getCaminhaoDesignado().getId())
                .orElseThrow(() -> new NotFoundException("Caminhão não encontrado"));

        if (itinerarioRepository.existsByRota_CaminhaoDesignado_IdAndDia(caminhao.getId(), dto.getDia())) {
            throw new BadRequestException("Este caminhão já possui itinerário registrado para este dia.");
        }

        Itinerario entity = ItinerarioMapper.toEntity(dto);

        entity.setRota(rota);

        Itinerario saved = itinerarioRepository.save(entity);
        return ItinerarioMapper.toDTO(saved);
    }

    public List<ItinerarioResponseDTO> listarTodos() {
        List<Itinerario> lista = itinerarioRepository.findAll();
        return ItinerarioMapper.toDTOList(lista);
    }

    public ItinerarioResponseDTO encontrarPorId(Long id) {
        Itinerario entity = itinerarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Itinerário não encontrado com ID: " + id));

        return ItinerarioMapper.toDTO(entity);
    }

    public ItinerarioResponseDTO atualizar(Long id, ItinerarioRequestDTO dto) {
        Itinerario entity = itinerarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Itinerário não encontrado com ID: " + id));

        Rota rota = rotaRepository.findById(dto.getRotaId())
                .orElseThrow(() -> new NotFoundException("Rota não encontrada"));

        Caminhao caminhao = caminhaoRepository.findById(rota.getCaminhaoDesignado().getId())
                .orElseThrow(() -> new NotFoundException("Caminhão não encontrado"));

        if (itinerarioRepository.existsByRota_CaminhaoDesignado_IdAndDia(caminhao.getId(), dto.getDia())) {
            throw new BadRequestException("Este caminhão já possui itinerário registrado para este dia.");
        }

        ItinerarioMapper.updateEntity(entity, dto);

        Itinerario updated = itinerarioRepository.save(entity);

        return ItinerarioMapper.toDTO(updated);
    }

    public List<ItinerarioResponseDTO> listarPorStatus(Boolean status) {
        return ItinerarioMapper.toDTOList(itinerarioRepository.findByAtivo(status));
    }

    // Ativar/Inativar
    public ItinerarioResponseDTO alterarStatus(Long id) {
        Itinerario itinerario = itinerarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Itinerario não encontrado com ID: " + id));
        itinerario.setAtivo(!itinerario.isAtivo());
        LogEventoSingleton log = LogEventoSingleton.getInstance();
        log.registrar("Status do Itinerario " + id + " alterado para " + itinerario.isAtivo());
        itinerarioRepository.save(itinerario);
        return ItinerarioMapper.toDTO(itinerario);
    }

    public void deletar(Long id) {
        if (!itinerarioRepository.existsById(id)) {
            throw new NotFoundException("Itinerário não encontrado com ID: " + id);
        }

        itinerarioRepository.deleteById(id);
    }
}
