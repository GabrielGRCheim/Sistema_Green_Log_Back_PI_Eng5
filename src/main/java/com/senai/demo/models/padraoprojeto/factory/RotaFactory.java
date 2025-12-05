package com.senai.demo.models.padraoprojeto.factory;


import com.senai.demo.models.entities.*;
import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.models.exceptions.BadRequestException;
import com.senai.demo.models.repositorys.PontoColetaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RotaFactory {

    private static String listLongToString(List<Long> list) {
        if (list == null || list.isEmpty()) return "";
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(";"));
    }

    /**
     * Método estático de criação (Factory Method)
     * Centraliza toda a lógica necessária para criar uma rota válida.
     */
    public static Rota criarRota(
            String nome,
            Caminhao caminhao,
            List<Bairro> bairros,
            List<RuaConexao> arestas,
            TipoResiduo tipoResiduo,
            PontoColetaRepository pontoColetaRepository
    ) {


        // 1. Validação básica obrigatória
        if (caminhao == null)
            throw new IllegalArgumentException("Caminhão não pode ser nulo.");

        if (bairros == null || bairros.isEmpty())
            throw new IllegalArgumentException("Lista de bairros não pode ser vazia.");

        if (arestas == null || arestas.isEmpty())
            throw new IllegalArgumentException("A lista de arestas não pode ser vazia.");



        // 2. Criar objeto Rota
        Rota rota = new Rota();

        // 3. Setar dados principais
        rota.setCaminhaoDesignado(caminhao);
        if(nome == null || nome.trim().isEmpty()){
            rota.setNome("Rota: " + bairros.get(bairros.size() - 1).getNome() + " - Caminhão Responsavel:" + caminhao.getMotorista());
        }else{
            rota.setNome(nome);
        }

        // 4. Armazenar os IDs dos bairros
                List<Long> bairrosIds = bairros.stream()
                        .map(Bairro::getId)
                        .toList();
                rota.setBairros(listLongToString(bairrosIds));

        // 5. Armazenar os IDs das arestas
                List<Long> arestasIds = arestas.stream()
                        .map(RuaConexao::getId)
                        .toList();
                rota.setArestas(listLongToString(arestasIds));


        // 6. Calcular distância total
        double distanciaTotal = arestas.stream()
                .mapToDouble(RuaConexao::getDistancia)
                .sum();
        rota.setDistancia_total((float) distanciaTotal);

        //9. Atribuir Tipo de Residuo
        if(tipoResiduo == null || !pontoColetaRepository.findByBairroId(bairrosIds.getLast()).getTiposResiduos().contains(tipoResiduo)){
            throw new BadRequestException("O tipo de residuo é obrigatorio e deve estar contido no ponto de coleta selecionado.");
        }else {
            rota.setTiposResiduos(tipoResiduo);
        }
        return rota;
    }
}
