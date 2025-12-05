package com.senai.demo.models.repositorys;

import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.enums.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao,Long> {
    Optional<Caminhao> findByPlaca(String placa);
    List<Caminhao> findByTiposResiduosContains(TipoResiduo tipo);
    List<Caminhao> findByAtivo(boolean b);
}
