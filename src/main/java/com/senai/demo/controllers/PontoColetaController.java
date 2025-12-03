package com.senai.demo.controllers;

import com.senai.demo.dtos.PontoColetaRequestDTO;
import com.senai.demo.dtos.PontoColetaResponseDTO;
import com.senai.demo.dtos.RotaResponseDTO;
import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.services.PontoColetaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontos-coleta")
@CrossOrigin(origins = "http://localhost:4200")
public class PontoColetaController {

    private final PontoColetaService pontoColetaService;

    public PontoColetaController(PontoColetaService pontoColetaService) {
        this.pontoColetaService = pontoColetaService;
    }

    // Criar
    @PostMapping
    public ResponseEntity<PontoColetaResponseDTO> criar(@Valid @RequestBody PontoColetaRequestDTO dto) {
        PontoColetaResponseDTO criado = pontoColetaService.criarPontoColeta(dto);
        return ResponseEntity.ok(criado);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<PontoColetaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pontoColetaService.listarTodos());
    }

    //Listar todos os Residuos Disponiveis no Enum
    @GetMapping("/tipos_residuo")
    public ResponseEntity<List<TipoResiduo>> listarTiposResiduo() {
        return ResponseEntity.ok(pontoColetaService.listarTiposResiduo());
    }

    // Listar todos Ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<PontoColetaResponseDTO>> listarAtivos() {
        return ResponseEntity.ok(pontoColetaService.listarAtivos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PontoColetaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pontoColetaService.encontrarPorId(id));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<PontoColetaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody PontoColetaRequestDTO dto
    ) {
        return ResponseEntity.ok(pontoColetaService.atualizar(id, dto));
    }

    // Atualizar Status
    @PutMapping("/status/{id}")
    public ResponseEntity<PontoColetaResponseDTO> alterarStatus(@PathVariable Long id,
                                                         @RequestBody boolean status) {
        return ResponseEntity.ok(pontoColetaService.alterarStatus(id, status));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pontoColetaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
