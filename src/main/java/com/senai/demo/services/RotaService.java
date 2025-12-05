package com.senai.demo.services;

import com.senai.demo.dtos.RotaRequestDTO;
import com.senai.demo.dtos.RotaResponseDTO;
import com.senai.demo.mappers.RotaMapper;
import com.senai.demo.models.entities.*;
import com.senai.demo.models.enums.TipoResiduo;
import com.senai.demo.models.exceptions.NotFoundException;
import com.senai.demo.models.padraoprojeto.factory.RotaFactory;
import com.senai.demo.models.padraoprojeto.singleton.LogEventoSingleton;
import com.senai.demo.models.repositorys.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RotaService {

    @Autowired
    private final RotaRepository rotaRepository;

    @Autowired
    private final CaminhaoRepository caminhaoRepository;

    @Autowired
    private final BairroRepository bairroRepository;

    @Autowired
    private final RuaConexaoRepository ruaConexaoRepository;

    @Autowired
    private final PontoColetaRepository pontoColetaRepository;

    public RotaService(RotaRepository rotaRepository, CaminhaoRepository caminhaoRepository, BairroRepository bairroRepository, RuaConexaoRepository ruaConexaoRepository, PontoColetaRepository pontoColetaRepository) {
        this.rotaRepository = rotaRepository;
        this.caminhaoRepository = caminhaoRepository;
        this.bairroRepository = bairroRepository;
        this.ruaConexaoRepository = ruaConexaoRepository;
        this.pontoColetaRepository = pontoColetaRepository;
    }

    public RotaResponseDTO criarRota(RotaRequestDTO dto) {

        Caminhao caminhao = caminhaoRepository.findById(dto.getCaminhaoId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Caminhão não encontrado com ID: " + dto.getCaminhaoId())
                );

        List<Bairro> bairros = bairroRepository.findAllById(dto.getBairros());
        List<RuaConexao> arestas = ruaConexaoRepository.findAllById(dto.getArestas());

        Rota rota = RotaFactory.criarRota(
                dto.getNome(),
                caminhao,
                bairros,
                arestas,
                dto.getTipoResiduo(),
                pontoColetaRepository
        );

        Rota saved = rotaRepository.save(rota);

        return RotaMapper.toDTO(saved);
    }


    public List<RotaResponseDTO> listarTodos() {
        return RotaMapper.toDTOList(rotaRepository.findAll());
    }

    public RotaResponseDTO encontrarPorId(Long id) {
        Rota rota = rotaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rota não encontrada com ID: " + id));

        return RotaMapper.toDTO(rota);
    }

    public RotaResponseDTO atualizar(Long id, RotaRequestDTO dto) {

        Rota rota = rotaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rota não encontrada"));

        // Atualiza caminhão
        Caminhao caminhao = caminhaoRepository.findById(dto.getCaminhaoId())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado"));

        List<Bairro> bairros = bairroRepository.findAllById(dto.getBairros());
        List<RuaConexao> arestas = ruaConexaoRepository.findAllById(dto.getArestas());

        Rota rotaVerificada = RotaFactory.criarRota(
                dto.getNome(),
                caminhao,
                bairros,
                arestas,
                dto.getTipoResiduo(),
                pontoColetaRepository
        );

        rota.setNome(rotaVerificada.getNome());
        rota.setBairros(rotaVerificada.getBairros());
        rota.setArestas(rotaVerificada.getArestas());
        rota.setCaminhaoDesignado(rotaVerificada.getCaminhaoDesignado());
        rota.setDistancia_total(rotaVerificada.getDistancia_total());
        rota.setTiposResiduos(rotaVerificada.getTiposResiduos());

        return RotaMapper.toDTO(rotaRepository.save(rota));
    }
    public List<RotaResponseDTO> listarAtivos() {
        return RotaMapper.toDTOList(rotaRepository.findByAtivo(true));
    }

    // Ativar/Inativar
    public RotaResponseDTO alterarStatus(Long id, boolean ativo) {
        Rota rota = rotaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rota não encontrado com ID: " + id));
        rota.setAtivo(ativo);
        LogEventoSingleton log = LogEventoSingleton.getInstance();
        log.registrar("Status da Rota " + id + " alterado para " + ativo);
        rotaRepository.save(rota);
        return RotaMapper.toDTO(rota);
    }

    public List<TipoResiduo> listarTiposResiduo() {
        return Arrays.stream(TipoResiduo.values()).toList();
    }

    public void deletar(Long id) {
        if (!rotaRepository.existsById(id)) {
            throw new EntityNotFoundException("Rota não encontrada com ID: " + id);
        }

        rotaRepository.deleteById(id);
    }
}
