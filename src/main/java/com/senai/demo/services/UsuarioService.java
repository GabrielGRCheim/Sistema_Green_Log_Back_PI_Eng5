package com.senai.demo.services;

import com.senai.demo.dtos.MotoristaResponseDTO;
import com.senai.demo.dtos.UsuarioRequestDTO;
import com.senai.demo.dtos.UsuarioResponseDTO;
import com.senai.demo.mappers.MotoristaMapper;
import com.senai.demo.mappers.UsuarioMapper;
import com.senai.demo.models.entities.Usuario;
import com.senai.demo.models.exceptions.NotFoundException;
import com.senai.demo.models.padraoprojeto.singleton.LogEventoSingleton;
import com.senai.demo.models.repositorys.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        Usuario entity = UsuarioMapper.toEntity(dto);
        entity.setSenha(passwordEncoder.encode(dto.getSenha()));
        Usuario saved = repository.save(entity);
        return UsuarioMapper.toDTO(saved);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return UsuarioMapper.toDTOList(repository.findAll());
    }

    public List<UsuarioResponseDTO> listarPorStatus(Boolean status) {
        return UsuarioMapper.toDTOList(repository.findByAtivo(status));
    }
    // Ativar/Inativar
    public UsuarioResponseDTO alterarStatus(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado com ID: " + id));
        usuario.setAtivo(!usuario.isAtivo());
        LogEventoSingleton log = LogEventoSingleton.getInstance();
        log.registrar("Status do usuário " + id + " alterado para " + usuario.isAtivo());
        repository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO EncontrarPorId(Long id) {
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        return UsuarioMapper.toDTO(entity);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        UsuarioMapper.updateEntity(entity, dto);

        Usuario updated = repository.save(entity);
        return UsuarioMapper.toDTO(updated);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);

        repository.deleteById(id);
    }
}
