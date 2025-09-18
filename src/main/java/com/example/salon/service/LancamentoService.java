package com.example.salon.service;

import com.example.salon.entity.Lancamento;
import com.example.salon.exception.ResourceNotFoundException;
import com.example.salon.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public List<Lancamento> findAll() {
        return lancamentoRepository.findAll();
    }

    public Optional<Lancamento> findById(Long id) {
        return lancamentoRepository.findById(id);
    }

    public Lancamento save(Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }

    public Lancamento partialUpdate(Long id, Lancamento lancamentoDetails) {
        Lancamento lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lançamento não encontrado com o id: " + id));

        if (lancamentoDetails.getColaborador() != null) {
            lancamento.setColaborador(lancamentoDetails.getColaborador());
        }
        if (lancamentoDetails.getServico() != null) {
            lancamento.setServico(lancamentoDetails.getServico());
        }
        if (lancamentoDetails.getData() != null) {
            lancamento.setData(lancamentoDetails.getData());
        }
        if (lancamentoDetails.getHora() != null) {
            lancamento.setHora(lancamentoDetails.getHora());
        }
        if (lancamentoDetails.getNomeCliente() != null) {
            lancamento.setNomeCliente(lancamentoDetails.getNomeCliente());
        }
        if (lancamentoDetails.getContatoCliente() != null) {
            lancamento.setContatoCliente(lancamentoDetails.getContatoCliente());
        }
        if (lancamentoDetails.getStatusServico() != null) {
            lancamento.setStatusServico(lancamentoDetails.getStatusServico());
        }
        if (lancamentoDetails.getMetodoPagamento() != null) {
            lancamento.setMetodoPagamento(lancamentoDetails.getMetodoPagamento());
        }

        return lancamentoRepository.save(lancamento);
    }

    public void deleteById(Long id) {
        lancamentoRepository.deleteById(id);
    }
}
