package com.senai.demo.controllers;

import com.senai.demo.dtos.BairroResponseDTO;
import com.senai.demo.dtos.RuaConexaoResponseDTO;
import com.senai.demo.services.GrafoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grafo")
@CrossOrigin(origins = "http://localhost:4200")
public class GrafoController {

    @Autowired
    private GrafoService grafoService;

    @GetMapping
    public Map<String, Object> getGrafo() {
        List<BairroResponseDTO> nodes = grafoService.getNodes();
        List<RuaConexaoResponseDTO> edges = grafoService.getEdges();

        Map<String, Object> response = new HashMap<>();
        response.put("nodes", nodes);
        response.put("edges", edges);

        return response;
    }
}