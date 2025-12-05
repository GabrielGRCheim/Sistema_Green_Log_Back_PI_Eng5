package com.senai.demo.models.repositorys;

import com.senai.demo.models.entities.Caminhao;
import com.senai.demo.models.entities.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario,Long> {
    List<Itinerario> findByAtivo(boolean b);
    boolean existsByRota_CaminhaoDesignado_IdAndDia(Long caminhaoId, LocalDate dia);
    boolean existsByDia(LocalDate dia);
}
