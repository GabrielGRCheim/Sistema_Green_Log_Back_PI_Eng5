package com.senai.demo.controllers;

import com.senai.demo.dtos.RotaRequestDTO;
import com.senai.demo.dtos.RotaResponseDTO;
import com.senai.demo.dtos.UsuarioResponseDTO;
import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.services.RotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotas")
@CrossOrigin(origins = "http://localhost:4200")
public class RotaController {

    private final RotaService rotaService;

    public RotaController(RotaService rotaService) {
        this.rotaService = rotaService;
    }

    // Criar
    @PostMapping
    public ResponseEntity<RotaResponseDTO> criar(@RequestBody RotaRequestDTO dto) {
        return ResponseEntity.ok(rotaService.criarRota(dto));
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<RotaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(rotaService.listarTodos());
    }

    //Listar todos os Residuos Disponiveis no Enum
    @GetMapping("/tipos_residuo")
    public ResponseEntity<List<TipoResiduo>> listarTiposResiduo() {
        return ResponseEntity.ok(rotaService.listarTiposResiduo());
    }

    // Listar todos Ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<RotaResponseDTO>> listarAtivos() {
        return ResponseEntity.ok(rotaService.listarAtivos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<RotaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rotaService.encontrarPorId(id));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<RotaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody RotaRequestDTO dto
    ) {
        return ResponseEntity.ok(rotaService.atualizar(id, dto));
    }

    // Atualizar Status
    @PutMapping("/status/{id}")
    public ResponseEntity<RotaResponseDTO> alterarStatus(@PathVariable Long id,
                                                            @RequestBody boolean status) {
        return ResponseEntity.ok(rotaService.alterarStatus(id, status));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        rotaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
