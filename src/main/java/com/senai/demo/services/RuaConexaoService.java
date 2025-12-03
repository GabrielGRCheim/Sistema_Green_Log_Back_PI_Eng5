package com.senai.demo.services;

import com.senai.demo.dtos.RuaConexaoRequestDTO;
import com.senai.demo.dtos.RuaConexaoResponseDTO;
import com.senai.demo.mappers.RuaConexaoMapper;
import com.senai.demo.models.entities.RuaConexao;
import com.senai.demo.models.repositorys.BairroRepository;
import com.senai.demo.models.repositorys.RuaConexaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuaConexaoService {

    private final RuaConexaoRepository repository;

    private final BairroRepository bairroRepository;

    public RuaConexaoService(RuaConexaoRepository repository, BairroRepository bairroRepository) {
        this.repository = repository;
        this.bairroRepository = bairroRepository;
    }

    public RuaConexaoResponseDTO criarRuaConexao(RuaConexaoRequestDTO dto) {
        if(dto.getNome().isBlank()){
            dto.setNome(bairroRepository.findById(dto.getOrigemId()).get().getNome() + " para " + bairroRepository.findById(dto.getDestinoId()).get().getNome());
        }
        RuaConexao entity = RuaConexaoMapper.toEntity(dto);
        RuaConexao saved = repository.save(entity);
        return RuaConexaoMapper.toDTO(saved);
    }

    public List<RuaConexaoResponseDTO> listarTodos() {
        return RuaConexaoMapper.toDTOList(repository.findAll());
    }

    public RuaConexaoResponseDTO encontrarPorID(Long id) {
        RuaConexao entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conexão de rua não encontrada com ID: " + id));
        return RuaConexaoMapper.toDTO(entity);
    }

    public RuaConexaoResponseDTO atualizar(Long id, RuaConexaoRequestDTO dto) {
        RuaConexao entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conexão de rua não encontrada com ID: " + id));

        RuaConexaoMapper.updateEntity(entity, dto);

        RuaConexao updated = repository.save(entity);
        return RuaConexaoMapper.toDTO(updated);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Conexão de rua não encontrada com ID: " + id);

        repository.deleteById(id);
    }
}
