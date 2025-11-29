package com.senai.demo.models.repositorys;

import com.senai.demo.models.entities.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta,Long> {
}
