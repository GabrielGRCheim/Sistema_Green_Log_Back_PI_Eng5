package com.senai.demo.controllers;

import com.senai.demo.dtos.RuaConexaoRequestDTO;
import com.senai.demo.dtos.RuaConexaoResponseDTO;
import com.senai.demo.services.RuaConexaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ruas-conexao")
@CrossOrigin(origins = "http://localhost:4200")
public class RuaConexaoController {

    private final RuaConexaoService ruaConexaoService;

    public RuaConexaoController(RuaConexaoService ruaConexaoService) {
        this.ruaConexaoService = ruaConexaoService;
    }

    // Criar
    @PostMapping
    public ResponseEntity<RuaConexaoResponseDTO> criar(@RequestBody RuaConexaoRequestDTO dto) {
        return ResponseEntity.ok(ruaConexaoService.criarRuaConexao(dto));
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<RuaConexaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(ruaConexaoService.listarTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<RuaConexaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ruaConexaoService.encontrarPorID(id));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<RuaConexaoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody RuaConexaoRequestDTO dto
    ) {
        return ResponseEntity.ok(ruaConexaoService.atualizar(id, dto));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ruaConexaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
