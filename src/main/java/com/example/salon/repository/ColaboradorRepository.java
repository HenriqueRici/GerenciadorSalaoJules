package com.example.salon.repository;

import com.example.salon.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    @Query("SELECT c FROM Colaborador c WHERE c.cpf = :cpf AND (c.dtFim IS NULL OR c.dtFim >= :dtInicio) AND (:dtFim IS NULL OR c.dtInicio <= :dtFim)")
    List<Colaborador> findByCpfAndOverlappingDateRange(@Param("cpf") String cpf, @Param("dtInicio") LocalDate dtInicio, @Param("dtFim") LocalDate dtFim);
}
