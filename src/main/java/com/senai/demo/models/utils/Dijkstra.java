package com.senai.demo.models.utils;

import com.senai.demo.models.entities.Bairro;
import com.senai.demo.models.entities.RuaConexao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {

    public ResultadoCaminho calcularCaminhoComParadas(List<RuaConexao> todasConexoes, List<Long> listaDeBairros,
                                                      Map<Long, Bairro> bairros) {
        if (listaDeBairros == null || listaDeBairros.size() < 2) {
            return new ResultadoCaminho(Collections.emptyList(), Collections.emptyList(), -1);
        }

        Map<Long, List<RuaConexao>> grafo = construirGrafoBidirecional(todasConexoes);

        List<Bairro> caminhoTotal = new ArrayList<>();
        List<RuaConexao> arestasTotal = new ArrayList<>();
        double distanciaTotal = 0.0;

        for (int i = 0; i < listaDeBairros.size() - 1; i++) {
            Long origem = listaDeBairros.get(i);
            Long destino = listaDeBairros.get(i + 1);

            ResultadoCaminho parcial = calcularMenorCaminho(grafo, origem, destino, bairros);

            if (parcial.getBairros().isEmpty()) {
                return new ResultadoCaminho(Collections.emptyList(), Collections.emptyList(), -1);
            }

            // Evita repetir o bairro intermediário
            if (!caminhoTotal.isEmpty()) {
                parcial.getBairros().remove(0);
            }

            caminhoTotal.addAll(parcial.getBairros());
            arestasTotal.addAll(parcial.getArestas());
            distanciaTotal += parcial.getDistanciaTotal();
        }

        return new ResultadoCaminho(caminhoTotal, arestasTotal, distanciaTotal);
    }

    private Map<Long, List<RuaConexao>> construirGrafoBidirecional(List<RuaConexao> todasConexoes) {
        Map<Long, List<RuaConexao>> grafo = new HashMap<>();

        for (RuaConexao conexao : todasConexoes) {
            grafo.computeIfAbsent(conexao.getOrigemId(), k -> new ArrayList<>()).add(conexao);

            // Cria e adiciona conexão inversa
            RuaConexao inversa = new RuaConexao(
                    conexao.getId(),
                    conexao.getDestinoId(),
                    conexao.getNome(),
                    conexao.getOrigemId(),
                    conexao.getDistancia());
            grafo.computeIfAbsent(inversa.getOrigemId(), k -> new ArrayList<>()).add(inversa);
        }

        return grafo;
    }

    public ResultadoCaminho calcularMenorCaminho(Map<Long, List<RuaConexao>> grafo, Long origem, Long destino,
                                                 Map<Long, Bairro> bairros) {
        Map<Long, Double> distancias = new HashMap<>();
        Map<Long, RuaConexao> predecessores = new HashMap<>();
        PriorityQueue<Long> fila = new PriorityQueue<>(Comparator.comparing(distancias::get));

        for (Long id : bairros.keySet()) {
            distancias.put(id, Double.MAX_VALUE);
        }
        distancias.put(origem, 0.0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Long atual = fila.poll();
            if (atual.equals(destino))
                break;
            if (!grafo.containsKey(atual))
                continue;

            for (RuaConexao conexao : grafo.get(atual)) {
                Long vizinho = conexao.getDestinoId();
                double novaDist = distancias.get(atual) + conexao.getDistancia();

                if (novaDist < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDist);
                    predecessores.put(vizinho, conexao);
                    fila.remove(vizinho);
                    fila.add(vizinho);
                }
            }
        }

        List<Bairro> caminho = new ArrayList<>();
        List<RuaConexao> arestas = new ArrayList<>();
        Long atual = destino;

        if (!predecessores.containsKey(destino)) {
            return new ResultadoCaminho(Collections.emptyList(), Collections.emptyList(), -1);
        }

        while (!atual.equals(origem)) {
            RuaConexao conexao = predecessores.get(atual);
            if (conexao == null)
                break;
            arestas.add(0, conexao);
            caminho.add(0, bairros.get(atual));
            atual = conexao.getOrigemId();
        }
        caminho.add(0, bairros.get(origem));

        double distanciaFinal = distancias.get(destino);
        if (distanciaFinal == Double.MAX_VALUE)
            distanciaFinal = -1;
        else
            distanciaFinal = new java.math.BigDecimal(distanciaFinal)
                    .setScale(2, java.math.RoundingMode.HALF_UP)
                    .doubleValue();

        return new ResultadoCaminho(caminho, arestas, distanciaFinal);
    }

    public static class ResultadoCaminho {
        private final List<Bairro> bairros;
        private final List<RuaConexao> arestas;
        private final double distanciaTotal;

        public ResultadoCaminho(List<Bairro> bairros, List<RuaConexao> arestas, double distanciaTotal) {
            this.bairros = bairros;
            this.arestas = arestas;
            this.distanciaTotal = distanciaTotal;
        }

        public List<Bairro> getBairros() {
            return bairros;
        }

        public List<RuaConexao> getArestas() {
            return arestas;
        }

        public double getDistanciaTotal() {
            return distanciaTotal;
        }
    }
}
