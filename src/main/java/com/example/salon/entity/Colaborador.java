package com.example.salon.entity;

import com.example.salon.validator.CPF;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser em branco")
    private String nome;

    @NotBlank(message = "CPF não pode ser em branco")
    @CPF
    private String cpf;

    private Double percentualComissao;
    private String chavePix;
    private LocalDate dtInicio;
    private LocalDate dtFim;
}
