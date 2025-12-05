package com.senai.demo.services;

import com.senai.demo.dtos.BairroResponseDTO;
import com.senai.demo.dtos.RuaConexaoResponseDTO;
import com.senai.demo.models.entities.Bairro;
import com.senai.demo.models.entities.RuaConexao;
import com.senai.demo.models.repositorys.BairroRepository;
import com.senai.demo.models.repositorys.RuaConexaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GrafoService {

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private RuaConexaoRepository rua_conexaoRepository;

    public List<BairroResponseDTO> getNodes() {
        List<Bairro> bairros = bairroRepository.findAll();
        return bairros.stream()
                .map(b -> new BairroResponseDTO(b.getId(), b.getNome()))
                .collect(Collectors.toList());
    }

    public List<RuaConexaoResponseDTO> getEdges() {
        List<RuaConexao> ruas = rua_conexaoRepository.findAll();
        return ruas.stream()
                .map(r -> new RuaConexaoResponseDTO(
                        r.getId(),
                        r.getOrigemId(),
                        r.getDestinoId(),
                        r.getDistancia()))
                .collect(Collectors.toList());
    }

    // Carrega todos os bairros e mapeia por ID
    public Map<Long, Bairro> carregarVertices() {
        return bairroRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Bairro::getId, bairro -> bairro));
    }

    // Novo método: carrega conexões planas (sem bidirecionalidade artificial)
    public List<RuaConexao> carregarTodasConexoes() {
        return rua_conexaoRepository.findAll();
    }

    public Map<Long, List<RuaConexao>> carregarArestas() {
        List<RuaConexao> arestas = rua_conexaoRepository.findAll();
        Map<Long, List<RuaConexao>> grafo = new HashMap<>();
        for (RuaConexao aresta : arestas) {
            grafo.computeIfAbsent(aresta.getOrigemId(), k -> new ArrayList<>())
                    .add(aresta);
        }
        return grafo;
    }
}