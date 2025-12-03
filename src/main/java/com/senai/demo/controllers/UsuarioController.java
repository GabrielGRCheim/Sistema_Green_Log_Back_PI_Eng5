package com.senai.demo.controllers;

import com.senai.demo.dtos.UsuarioRequestDTO;
import com.senai.demo.dtos.UsuarioResponseDTO;
import com.senai.demo.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // Criar novo usuário
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO criado = service.criarUsuario(dto);
        return ResponseEntity.created(URI.create("/usuarios/" + criado.getId())).body(criado);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Listar todos Ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<UsuarioResponseDTO>> listarAtivos() {
        return ResponseEntity.ok(service.listarAtivos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.EncontrarPorId(id));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    // Atualizar Status
    @PutMapping("/status/{id}")
    public ResponseEntity<UsuarioResponseDTO> alterarStatus(@PathVariable Long id,
                                                        @RequestBody boolean status) {
        return ResponseEntity.ok(service.alterarStatus(id, status));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
