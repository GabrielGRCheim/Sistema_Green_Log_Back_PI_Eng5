package com.senai.demo.controllers;

import com.senai.demo.dtos.CaminhaoRequestDTO;
import com.senai.demo.dtos.CaminhaoResponseDTO;
import com.senai.demo.services.CaminhaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caminhoes")
@CrossOrigin(origins = "http://localhost:4200")
public class CaminhaoController {

    private final CaminhaoService caminhaoService;

    public CaminhaoController(CaminhaoService caminhaoService) {
        this.caminhaoService = caminhaoService;
    }

    // Criar
    @PostMapping
    public ResponseEntity<CaminhaoResponseDTO> criar(@RequestBody CaminhaoRequestDTO dto) {
        CaminhaoResponseDTO criado = caminhaoService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<CaminhaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(caminhaoService.listarTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<CaminhaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(caminhaoService.buscarPorId(id));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<CaminhaoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody CaminhaoRequestDTO dto
    ) {
        return ResponseEntity.ok(caminhaoService.atualizar(id, dto));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        caminhaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
