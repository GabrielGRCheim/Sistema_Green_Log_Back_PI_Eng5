package com.senai.demo.models.repositorys;

import com.senai.demo.models.entities.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao,Long> {
    Optional<Caminhao> findByPlaca(String placa);

}
