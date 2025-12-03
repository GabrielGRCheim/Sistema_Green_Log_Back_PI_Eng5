package com.senai.demo.services;

import com.senai.demo.dtos.CaminhaoRequestDTO;
import com.senai.demo.dtos.CaminhaoResponseDTO;
import com.senai.demo.dtos.MotoristaResponseDTO;
import com.senai.demo.dtos.PontoColetaResponseDTO;
import com.senai.demo.mappers.CaminhaoMapper;
import com.senai.demo.mappers.MotoristaMapper;
import com.senai.demo.mappers.PontoColetaMapper;
import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Motorista;
import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.models.exceptions.BadRequestException;
import com.senai.demo.models.exceptions.ConflictException;
import com.senai.demo.models.exceptions.NotFoundException;
import com.senai.demo.models.padraoprojeto.singleton.LogEventoSingleton;
import com.senai.demo.models.repositorys.CaminhaoRepository;
import com.senai.demo.models.repositorys.MotoristaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoService {

    private final CaminhaoRepository caminhaoRepository;

    private final MotoristaRepository motoristaRepository;

    public CaminhaoService(CaminhaoRepository caminhaoRepository, MotoristaRepository motoristaRepository) {
        this.caminhaoRepository = caminhaoRepository;
        this.motoristaRepository = motoristaRepository;
    }

    // CREATE
    public CaminhaoResponseDTO criar(CaminhaoRequestDTO dto) {

        // Validação de placa duplicada
        if (caminhaoRepository.findByPlaca(dto.getPlaca()).isPresent()) {
            throw new ConflictException("Já existe um caminhão cadastrado com essa placa.");
        }
        Motorista motorista = motoristaRepository.findById(dto.getMotorista_id())
                .orElseThrow(() -> new NotFoundException("Motorista não encontrado."));

        Caminhao caminhao = CaminhaoMapper.toEntity(dto);
        caminhao.setMotorista(motorista);
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

    public List<CaminhaoResponseDTO> listarAtivos() {
        return CaminhaoMapper.toDTOList(caminhaoRepository.findByAtivo(true));
    }

    // Ativar/Inativar
    public CaminhaoResponseDTO alterarStatus(Long id, boolean ativo) {
        Caminhao caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Caminhão não encontrado com ID: " + id));
        caminhao.setAtivo(ativo);
        LogEventoSingleton log = LogEventoSingleton.getInstance();
        log.registrar("Status do Caminhão " + id + " alterado para " + ativo);
        caminhaoRepository.save(caminhao);
        return CaminhaoMapper.toDTO(caminhao);
    }

    public List<TipoResiduo> listarTiposResiduo() {
        return Arrays.stream(TipoResiduo.values()).toList();
    }

    // DELETAR
    public void deletar(Long id) {
        Caminhao caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Caminhão não encontrado."));

        caminhaoRepository.delete(caminhao);
    }
}
