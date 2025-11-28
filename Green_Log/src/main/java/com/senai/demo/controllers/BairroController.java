package com.senai.demo.controllers;

import com.senai.demo.dtos.BairroRequestDTO;
import com.senai.demo.dtos.BairroResponseDTO;
import com.senai.demo.services.BairroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bairros")
@CrossOrigin(origins = "http://localhost:4200")
public class BairroController {

    private final BairroService bairroService;

    public BairroController(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    // Criar
    @PostMapping
    public ResponseEntity<BairroResponseDTO> criar(@RequestBody BairroRequestDTO dto) {
        BairroResponseDTO criado = bairroService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<BairroResponseDTO>> listarTodos() {
        return ResponseEntity.ok(bairroService.listarTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<BairroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bairroService.buscarPorId(id));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<BairroResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody BairroRequestDTO dto
    ) {
        return ResponseEntity.ok(bairroService.atualizar(id, dto));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        bairroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
