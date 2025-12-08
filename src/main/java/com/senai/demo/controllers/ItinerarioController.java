package com.senai.demo.controllers;

import com.senai.demo.dtos.ItinerarioRequestDTO;
import com.senai.demo.dtos.ItinerarioResponseDTO;
import com.senai.demo.dtos.MotoristaResponseDTO;
import com.senai.demo.dtos.PontoColetaResponseDTO;
import com.senai.demo.services.ItinerarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itinerarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ItinerarioController {

    private final ItinerarioService itinerarioService;

    public ItinerarioController(ItinerarioService itinerarioService) {
        this.itinerarioService = itinerarioService;
    }

    @PostMapping
    public ResponseEntity<ItinerarioResponseDTO> criar(@RequestBody ItinerarioRequestDTO dto) {
        return ResponseEntity.ok(itinerarioService.criarItinerario(dto));
    }

    @GetMapping
    public ResponseEntity<List<ItinerarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(itinerarioService.listarTodos());
    }

    @GetMapping("/Status")
    public ResponseEntity<List<ItinerarioResponseDTO>> listarPorStatus(
            @RequestParam Boolean status
    ) {
        return ResponseEntity.ok(itinerarioService.listarPorStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItinerarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itinerarioService.encontrarPorId(id));
    }

    // Atualizar Status
    @PatchMapping("/status/{id}")
    public ResponseEntity<ItinerarioResponseDTO> alterarStatus(@PathVariable Long id) {
        return ResponseEntity.ok(itinerarioService.alterarStatus(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItinerarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ItinerarioRequestDTO dto
    ) {
        return ResponseEntity.ok(itinerarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itinerarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
