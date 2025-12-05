package com.senai.demo.models.repositorys;

import com.senai.demo.models.entities.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    List<Motorista> findByAtivo(boolean b);
}
