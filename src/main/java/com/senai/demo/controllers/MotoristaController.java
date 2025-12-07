package com.senai.demo.controllers;

import com.senai.demo.dtos.MotoristaRequestDTO;
import com.senai.demo.dtos.MotoristaResponseDTO;
import com.senai.demo.dtos.PontoColetaResponseDTO;
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

    // Listar todos Ativos
    @GetMapping("/Status")
    public ResponseEntity<List<MotoristaResponseDTO>> listarPorStatus(
            @RequestParam Boolean status
    ) {
        return ResponseEntity.ok(motoristaService.listarPorStatus(status));
    }


    // Alterar status (ativar/inativar)
    @PatchMapping("/status/{id}")
    public ResponseEntity<MotoristaResponseDTO> alterarStatus(@PathVariable Long id) {
        return ResponseEntity.ok(motoristaService.alterarStatus(id));
    }

    // Deletar motorista
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        motoristaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
