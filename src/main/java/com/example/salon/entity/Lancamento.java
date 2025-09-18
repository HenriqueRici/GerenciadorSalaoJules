package com.example.salon.entity;

import com.example.salon.enums.MetodoPagamento;
import com.example.salon.enums.StatusServico;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    private LocalDate data;
    private LocalTime hora;
    private String nomeCliente;
    private String contatoCliente;

    @Enumerated(EnumType.STRING)
    private StatusServico statusServico;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
}
