package com.caixadesugestoes.repository;

import com.caixadesugestoes.model.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, Long> {
    List<Sugestao> findTop3ByOrderByCurtidasDesc();
}
