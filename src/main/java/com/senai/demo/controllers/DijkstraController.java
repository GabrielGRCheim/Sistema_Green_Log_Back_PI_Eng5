package com.senai.demo.controllers;

import com.senai.demo.models.entities.Bairro;
import com.senai.demo.models.entities.RuaConexao;
import com.senai.demo.models.utils.Dijkstra;
import com.senai.demo.services.GrafoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/dijkstra")
public class DijkstraController {

    @Autowired
    private GrafoService grafoService;

    @PostMapping("/caminho_com_paradas")
    public ResponseEntity<Map<String, Object>> calcularCaminhoComParadas(@RequestBody List<Long> listaDeBairros) {
        if (listaDeBairros == null || listaDeBairros.size() < 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("erro", "Forneça ao menos dois bairros."));
        }

        // Carrega os dados do banco
        Map<Long, Bairro> vertices = grafoService.carregarVertices();
        List<RuaConexao> todasConexoes = grafoService.carregarArestas().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // Executa Dijkstra com múltiplas paradas
        Dijkstra dijkstra = new Dijkstra();
        Dijkstra.ResultadoCaminho resultado = dijkstra.calcularCaminhoComParadas(todasConexoes, listaDeBairros,
                vertices);

        if (resultado.getDistanciaTotal() == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", "Não foi possível encontrar o caminho entre todos os bairros."));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("distanciaTotal", resultado.getDistanciaTotal());
        response.put("caminho", resultado.getBairros());
        response.put("arestas", resultado.getArestas());

        return ResponseEntity.ok(response);
    }
}