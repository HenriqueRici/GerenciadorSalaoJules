package com.example.salon.enums;

public enum StatusServico {
    AGENDADO("Agendado"),
    CONCLUIDO("Concluído"),
    CANCELADO("Cancelado"),
    REMARCADO("Remarcado");

    private final String descricao;

    StatusServico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
