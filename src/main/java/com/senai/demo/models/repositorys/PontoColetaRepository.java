package com.senai.demo.models.repositorys;

import com.senai.demo.models.entities.PontoColeta;
import com.senai.demo.models.entities.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta,Long> {

    PontoColeta findByBairroId(Long bairroId);

    List<PontoColeta> findByAtivo(boolean b);
}
