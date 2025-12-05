package com.senai.demo.models.repositorys;

import com.senai.demo.models.entities.Rota;
import com.senai.demo.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Long> {
    List<Rota> findByAtivo(boolean b);
}
