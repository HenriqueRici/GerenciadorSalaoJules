package com.example.salon.repository;

import com.example.salon.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT s FROM Servico s WHERE s.tipoServico = :tipoServico AND (s.dtFim IS NULL OR s.dtFim >= :dtInicio) AND (:dtFim IS NULL OR s.dtInicio <= :dtFim)")
    List<Servico> findByTipoServicoAndOverlappingDateRange(@Param("tipoServico") String tipoServico, @Param("dtInicio") LocalDate dtInicio, @Param("dtFim") LocalDate dtFim);
}
