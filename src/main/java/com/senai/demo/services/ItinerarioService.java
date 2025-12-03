package com.senai.demo.services;

import com.senai.demo.dtos.ItinerarioRequestDTO;
import com.senai.demo.dtos.ItinerarioResponseDTO;
import com.senai.demo.mappers.ItinerarioMapper;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Itinerario;
import com.senai.demo.models.entities.Rota;
import com.senai.demo.models.exceptions.NotFoundException;
import com.senai.demo.models.padraoprojeto.singleton.LogEventoSingleton;
import com.senai.demo.models.repositorys.CaminhaoRepository;
import com.senai.demo.models.repositorys.ItinerarioRepository;
import com.senai.demo.models.repositorys.RotaRepository;
import jakarta.persistence.EntityNotFoundException;
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

        Caminhao caminhao = caminhaoRepository.findById(dto.getCaminhaoId())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado"));

        Rota rota = rotaRepository.findById(dto.getRotaId())
                .orElseThrow(() -> new EntityNotFoundException("Rota não encontrada"));

        Itinerario entity = ItinerarioMapper.toEntity(dto);

        entity.setCaminhao(caminhao);
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
                .orElseThrow(() -> new EntityNotFoundException("Itinerário não encontrado com ID: " + id));

        return ItinerarioMapper.toDTO(entity);
    }

    public ItinerarioResponseDTO atualizar(Long id, ItinerarioRequestDTO dto) {
        Itinerario entity = itinerarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Itinerário não encontrado com ID: " + id));

        Caminhao caminhao = caminhaoRepository.findById(dto.getCaminhaoId())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado"));

        Rota rota = rotaRepository.findById(dto.getRotaId())
                .orElseThrow(() -> new EntityNotFoundException("Rota não encontrada"));

        ItinerarioMapper.updateEntity(entity, dto);

        Itinerario updated = itinerarioRepository.save(entity);

        return ItinerarioMapper.toDTO(updated);
    }

    public List<ItinerarioResponseDTO> listarAtivos() {
        return ItinerarioMapper.toDTOList(itinerarioRepository.findByAtivo(true));
    }

    // Ativar/Inativar
    public ItinerarioResponseDTO alterarStatus(Long id, boolean ativo) {
        Itinerario itinerario = itinerarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Itinerario não encontrado com ID: " + id));
        itinerario.setAtivo(ativo);
        LogEventoSingleton log = LogEventoSingleton.getInstance();
        log.registrar("Status do Itinerario " + id + " alterado para " + ativo);
        itinerarioRepository.save(itinerario);
        return ItinerarioMapper.toDTO(itinerario);
    }

    public void deletar(Long id) {
        if (!itinerarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Itinerário não encontrado com ID: " + id);
        }

        itinerarioRepository.deleteById(id);
    }
}
