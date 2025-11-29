package com.senai.demo.controllers;

import com.senai.demo.dtos.LoginRequestDTO;
import com.senai.demo.dtos.LoginResponseDTO;
import com.senai.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        LoginResponseDTO resposta = authService.autenticar(dto.getEmail(), dto.getSenha());

        if (!resposta.isAutenticado()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resposta);
        }

        return ResponseEntity.ok(resposta);
    }
}
