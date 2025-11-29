package com.senai.demo.models.repositorys;

import com.senai.demo.models.entities.RuaConexao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuaConexaoRepository extends JpaRepository<RuaConexao,Long> {
}
