package com.senai.demo.controllers;

import com.senai.demo.dtos.CaminhaoRequestDTO;
import com.senai.demo.dtos.CaminhaoResponseDTO;
import com.senai.demo.dtos.ItinerarioResponseDTO;
import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.services.CaminhaoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CaminhaoResponseDTO> criar(@Valid @RequestBody CaminhaoRequestDTO dto) {
        CaminhaoResponseDTO criado = caminhaoService.criar(dto);
        return ResponseEntity.ok(criado);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<CaminhaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(caminhaoService.listarTodos());
    }

    // Listar todos Ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<CaminhaoResponseDTO>> listarAtivos() {
        return ResponseEntity.ok(caminhaoService.listarAtivos());
    }

    //Listar todos os Residuos Disponiveis no Enum
    @GetMapping("/tipos_residuo")
    public ResponseEntity<List<TipoResiduo>> listarTiposResiduo() {
        return ResponseEntity.ok(caminhaoService.listarTiposResiduo());
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
            @Valid @RequestBody CaminhaoRequestDTO dto
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
