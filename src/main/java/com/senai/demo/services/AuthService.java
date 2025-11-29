package com.senai.demo.services;

import com.senai.demo.dtos.LoginResponseDTO;
import com.senai.demo.models.entities.Usuario;
import com.senai.demo.models.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponseDTO autenticar(String email, String senha) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            return new LoginResponseDTO("Erro ao realizar login", false);
        }

        boolean senhaCorreta = passwordEncoder.matches(senha, usuario.getSenha());

        if (!senhaCorreta) {
            return new LoginResponseDTO("Erro ao realizar login", false);
        }

        return new LoginResponseDTO("Autenticado com sucesso", true);
    }
}
