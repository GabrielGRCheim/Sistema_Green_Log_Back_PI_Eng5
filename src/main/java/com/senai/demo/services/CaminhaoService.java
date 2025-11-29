package com.senai.demo.services;

import com.senai.demo.dtos.CaminhaoRequestDTO;
import com.senai.demo.dtos.CaminhaoResponseDTO;
import com.senai.demo.mappers.CaminhaoMapper;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.exceptions.BadRequestException;
import com.senai.demo.models.exceptions.ConflictException;
import com.senai.demo.models.exceptions.NotFoundException;
import com.senai.demo.models.repositorys.CaminhaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoService {

    private final CaminhaoRepository caminhaoRepository;

    public CaminhaoService(CaminhaoRepository caminhaoRepository) {
        this.caminhaoRepository = caminhaoRepository;
    }

    // CREATE
    public CaminhaoResponseDTO criar(CaminhaoRequestDTO dto) {

        // Validação de placa duplicada
        if (caminhaoRepository.findByPlaca(dto.getPlaca()).isPresent()) {
            throw new ConflictException("Já existe um caminhão cadastrado com essa placa.");
        }

        Caminhao caminhao = CaminhaoMapper.toEntity(dto);
        Caminhao salvo = caminhaoRepository.save(caminhao);

        return CaminhaoMapper.toDTO(salvo);
    }

    // LISTAR TODOS
    public List<CaminhaoResponseDTO> listarTodos() {
        return caminhaoRepository.findAll()
                .stream()
                .map(CaminhaoMapper::toDTO)
                .toList();
    }

    // BUSCAR POR ID
    public CaminhaoResponseDTO buscarPorId(Long id) {
        Caminhao caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Caminhão não encontrado."));
        return CaminhaoMapper.toDTO(caminhao);
    }

    // ATUALIZAR
    public CaminhaoResponseDTO atualizar(Long id, CaminhaoRequestDTO dto) {

        Caminhao existente = caminhaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Caminhão não encontrado."));

        // Validar placa duplicada em outros registros
        Optional<Caminhao> outroComMesmaPlaca = caminhaoRepository.findByPlaca(dto.getPlaca());
        if (outroComMesmaPlaca.isPresent() && !outroComMesmaPlaca.get().getId().equals(id)) {
            throw new ConflictException("Já existe outro caminhão com essa placa.");
        }

        CaminhaoMapper.updateEntity(existente, dto);

        Caminhao atualizado = caminhaoRepository.save(existente);
        return CaminhaoMapper.toDTO(atualizado);
    }

    // DELETAR
    public void deletar(Long id) {
        Caminhao caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Caminhão não encontrado."));

        caminhaoRepository.delete(caminhao);
    }
}
