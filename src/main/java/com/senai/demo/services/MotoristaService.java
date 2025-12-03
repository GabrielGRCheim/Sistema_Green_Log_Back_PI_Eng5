package com.senai.demo.services;

import com.senai.demo.dtos.MotoristaRequestDTO;
import com.senai.demo.dtos.MotoristaResponseDTO;
import com.senai.demo.mappers.MotoristaMapper;
import com.senai.demo.models.entities.Motorista;
import com.senai.demo.models.exceptions.NotFoundException;
import com.senai.demo.models.padraoprojeto.singleton.LogEventoSingleton;
import com.senai.demo.models.repositorys.MotoristaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;

    public MotoristaService(MotoristaRepository motoristaRepository) {
        this.motoristaRepository = motoristaRepository;
    }

    // Criar um novo motorista
    public MotoristaResponseDTO criar(MotoristaRequestDTO dto) {
        Motorista motorista = MotoristaMapper.toEntity(dto);
        motoristaRepository.save(motorista);
        return MotoristaMapper.toDTO(motorista);
    }

    // Buscar por ID
    public MotoristaResponseDTO buscarPorId(Long id) {
         Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado com ID: " + id));
        return MotoristaMapper.toDTO(motorista);
    }

    // Listar todos os motoristas
    public List<MotoristaResponseDTO> listarTodos() {
        return MotoristaMapper.toDTOList(motoristaRepository.findAll());
    }

    // Atualizar motorista
    public MotoristaResponseDTO atualizar(Long id, MotoristaRequestDTO dto) {
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Motorista não encontrado com ID: " + id));

        MotoristaMapper.updateEntity(motorista, dto);

        motoristaRepository.save(motorista);
        return MotoristaMapper.toDTO(motorista);
    }

    public List<MotoristaResponseDTO> listarAtivos() {
        return MotoristaMapper.toDTOList(motoristaRepository.findByAtivo(true));
    }

    // Ativar/Inativar motorista
    public MotoristaResponseDTO alterarStatus(Long id, boolean ativo) {
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Motorista não encontrado com ID: " + id));
        motorista.setAtivo(ativo);
        LogEventoSingleton log = LogEventoSingleton.getInstance();
        log.registrar("Status do Motorista " + id + " alterado para " + ativo);
        motoristaRepository.save(motorista);
        return MotoristaMapper.toDTO(motorista);
    }

    // Deletar motorista
    public void deletar(Long id) {
        if (!motoristaRepository.existsById(id)) {
            throw new NotFoundException("Motorista não encontrado para exclusão: " + id);
        }
        motoristaRepository.deleteById(id);
    }
}
