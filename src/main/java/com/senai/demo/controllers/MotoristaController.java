package com.senai.demo.controllers;

import com.senai.demo.dtos.MotoristaRequestDTO;
import com.senai.demo.dtos.MotoristaResponseDTO;
import com.senai.demo.services.MotoristaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
@CrossOrigin(origins = "http://localhost:4200")
public class MotoristaController {

    private final MotoristaService motoristaService;

    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    // Criar motorista
    @PostMapping
    public ResponseEntity<MotoristaResponseDTO> criar(@Valid @RequestBody MotoristaRequestDTO dto) {
        return ResponseEntity.ok(motoristaService.criar(dto));
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<MotoristaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoristaService.buscarPorId(id));
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<MotoristaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(motoristaService.listarTodos());
    }

    // Atualizar motorista
    @PutMapping("/{id}")
    public ResponseEntity<MotoristaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody MotoristaRequestDTO dto) {

        return ResponseEntity.ok(motoristaService.atualizar(id, dto));
    }

    // Alterar status (ativar/inativar)
    @PatchMapping("/{id}/status")
    public ResponseEntity<MotoristaResponseDTO> alterarStatus(
            @PathVariable Long id,
            @RequestParam boolean ativo) {

        return ResponseEntity.ok(motoristaService.alterarStatus(id, ativo));
    }

    // Deletar motorista
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        motoristaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
