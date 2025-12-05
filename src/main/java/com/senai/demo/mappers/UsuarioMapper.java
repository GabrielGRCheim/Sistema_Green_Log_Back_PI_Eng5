package com.senai.demo.mappers;

import com.senai.demo.dtos.UsuarioRequestDTO;
import com.senai.demo.dtos.UsuarioResponseDTO;
import com.senai.demo.models.entities.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    // RequestDTO → Entity
    public static Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return usuario;
    }

    // Entity → ResponseDTO
    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setAtivo(usuario.isAtivo());

        return dto;
    }

    // Atualizar entidade existente a partir do DTO
    public static void updateEntity(Usuario usuario, UsuarioRequestDTO dto) {
        if (usuario == null || dto == null) return;

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        // Atualizar senha somente se for enviada e não estiver vazia
        if (dto.getSenha() != null && !dto.getSenha().trim().isEmpty()) {
            usuario.setSenha(dto.getSenha());
        }
    }


    // Lista de entidades → Lista de DTO
    public static List<UsuarioResponseDTO> toDTOList(List<Usuario> usuarios) {
        if (usuarios == null) return null;

        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
