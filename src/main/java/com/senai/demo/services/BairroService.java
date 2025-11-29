package com.senai.demo.services;

import com.senai.demo.dtos.BairroRequestDTO;
import com.senai.demo.dtos.BairroResponseDTO;
import com.senai.demo.mappers.BairroMapper;
import com.senai.demo.models.entities.Bairro;
import com.senai.demo.models.exceptions.NotFoundException;
import com.senai.demo.models.repositorys.BairroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {

    private final BairroRepository bairroRepository;

    public BairroService(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }

    // CRIAR BAIRRO
    public BairroResponseDTO criar(BairroRequestDTO dto) {

        Bairro bairro = BairroMapper.toEntity(dto);
        Bairro salvo = bairroRepository.save(bairro);

        return BairroMapper.toDTO(salvo);
    }

    // LISTAR TODOS
    public List<BairroResponseDTO> listarTodos() {
        return BairroMapper.toDTOList(bairroRepository.findAll());
    }

    // BUSCAR POR ID
    public BairroResponseDTO buscarPorId(Long id) {
        Bairro bairro = bairroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bairro não encontrado!"));

        return BairroMapper.toDTO(bairro);
    }

    // ATUALIZAR
    public BairroResponseDTO atualizar(Long id, BairroRequestDTO dto) {

        Bairro bairro = bairroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bairro não encontrado!"));

        BairroMapper.updateEntity(bairro, dto);

        Bairro atualizado = bairroRepository.save(bairro);

        return BairroMapper.toDTO(atualizado);
    }

    // DELETAR
    public void deletar(Long id) {
        if (!bairroRepository.existsById(id)) {
            throw new NotFoundException("Bairro não encontrado!");
        }

        bairroRepository.deleteById(id);
    }
}
