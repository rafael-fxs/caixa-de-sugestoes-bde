package com.caixadesugestoes.repository;

import com.caixadesugestoes.model.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, Long> {
    @Query(value = "SELECT * FROM sugestao ORDER BY 1 DESC", nativeQuery = true)
    List<Sugestao> listarTodas();


    @Modifying
    @Query(value = "INSERT INTO sugestao (descricao, categoria, usuario_id) VALUES (:descricao, :categoria, :usuarioId)", nativeQuery = true)
    void enviarSugestao(@Param("descricao") String descricao, @Param("categoria") String categoria, @Param("usuarioId") Long usuarioId);

    @Query(value = "SELECT COUNT(*) FROM curtidas WHERE sugestao_id = :sugestaoId AND usuario_id = :usuarioId", nativeQuery = true)
    int verificarCurtida(@Param("sugestaoId") Long sugestaoId, @Param("usuarioId") Long usuarioId);

    @Modifying
    @Query(value = "INSERT INTO curtidas (sugestao_id, usuario_id) VALUES (:sugestaoId, :usuarioId)", nativeQuery = true)
    void adicionarCurtida(@Param("sugestaoId") Long sugestaoId, @Param("usuarioId") Long usuarioId);

    @Modifying
    @Query(value = "DELETE FROM curtidas WHERE sugestao_id = :sugestaoId AND usuario_id = :usuarioId", nativeQuery = true)
    void removerCurtida(@Param("sugestaoId") Long sugestaoId, @Param("usuarioId") Long usuarioId);

    @Query(value = """
    SELECT s.* 
    FROM sugestao s
    LEFT JOIN curtidas c ON s.id = c.sugestao_id
    GROUP BY s.id
    ORDER BY COUNT(c.sugestao_id) DESC
    LIMIT 3
    """, nativeQuery = true)
    List<Sugestao> listarTop3DaSemana();

    @Modifying
    @Query(value = "DELETE FROM curtidas WHERE sugestao_id = :sugestaoId", nativeQuery = true)
    void deletarCurtidasPorSugestao(@Param("sugestaoId") Long sugestaoId);

    @Modifying
    @Query(value = "DELETE FROM sugestao WHERE id = :sugestaoId", nativeQuery = true)
    void deletarSugestao(@Param("sugestaoId") Long sugestaoId);
}
