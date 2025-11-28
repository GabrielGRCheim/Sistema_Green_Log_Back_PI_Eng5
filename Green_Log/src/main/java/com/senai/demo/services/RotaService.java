package com.senai.demo.services;

import com.senai.demo.dtos.RotaRequestDTO;
import com.senai.demo.dtos.RotaResponseDTO;
import com.senai.demo.mappers.RotaMapper;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Rota;
import com.senai.demo.models.repositorys.CaminhaoRepository;
import com.senai.demo.models.repositorys.RotaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotaService {

    private final RotaRepository rotaRepository;
    private final CaminhaoRepository caminhaoRepository;

    public RotaService(RotaRepository rotaRepository, CaminhaoRepository caminhaoRepository) {
        this.rotaRepository = rotaRepository;
        this.caminhaoRepository = caminhaoRepository;
    }

    public RotaResponseDTO criarRota(RotaRequestDTO dto) {

        // Criar rota sem caminhão
        Rota rota = RotaMapper.toEntity(dto);

        // Buscar caminhão pelo ID enviado no DTO
        Caminhao caminhao = caminhaoRepository.findById(dto.getCaminhaoId())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado com ID: " + dto.getCaminhaoId()));

        rota.setCaminhaoDesignado(caminhao);

        Rota saved = rotaRepository.save(rota);

        return RotaMapper.toDTO(saved);
    }

    public List<RotaResponseDTO> listarTodos() {
        return RotaMapper.toDTOList(rotaRepository.findAll());
    }

    public RotaResponseDTO encontrarPorId(Long id) {
        Rota rota = rotaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rota não encontrada com ID: " + id));

        return RotaMapper.toDTO(rota);
    }

    public RotaResponseDTO atualizar(Long id, RotaRequestDTO dto) {

        Rota rota = rotaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rota não encontrada com ID: " + id));

        // Atualiza campos básicos
        RotaMapper.updateEntity(rota, dto);

        // Atualização do caminhão
        Caminhao caminhao = caminhaoRepository.findById(dto.getCaminhaoId())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado com ID: " + dto.getCaminhaoId()));

        rota.setCaminhaoDesignado(caminhao);

        Rota updated = rotaRepository.save(rota);

        return RotaMapper.toDTO(updated);
    }

    public void deletar(Long id) {
        if (!rotaRepository.existsById(id)) {
            throw new EntityNotFoundException("Rota não encontrada com ID: " + id);
        }

        rotaRepository.deleteById(id);
    }
}
