package com.senai.demo.models.utils;

import com.senai.demo.dtos.RuaConexaoResponseDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GrafoBuilder {

    public static Map<Long, List<RuaConexaoResponseDTO>> construirGrafoBidirecional(List<RuaConexaoResponseDTO> conexoes) {
        Map<Long, List<RuaConexaoResponseDTO>> grafo = new HashMap<>();

        for (RuaConexaoResponseDTO conexao : conexoes) {
            Long origem = conexao.getOrigemId();
            Long destino = conexao.getDestinoId();
            float distancia = conexao.getDistancia();

            // Criando a Rua_Conexao com setters
            RuaConexaoResponseDTO arestaOriginal = new RuaConexaoResponseDTO(destino, destino, destino, distancia);
            arestaOriginal.setId(conexao.getId());
            arestaOriginal.setOrigemId(origem);
            arestaOriginal.setDestinoId(destino);
            arestaOriginal.setDistancia(distancia);

            RuaConexaoResponseDTO arestaInvertida = new RuaConexaoResponseDTO(destino, destino, destino, distancia);
            arestaInvertida.setId(conexao.getId());
            arestaInvertida.setOrigemId(destino);
            arestaInvertida.setDestinoId(origem);
            arestaInvertida.setDistancia(distancia);

            grafo.computeIfAbsent(origem, k -> new ArrayList<>()).add(arestaOriginal);
            grafo.computeIfAbsent(destino, k -> new ArrayList<>()).add(arestaInvertida);
        }

        return grafo;
    }
}
