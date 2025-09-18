package com.example.salon.controller;

import com.example.salon.entity.Lancamento;
import com.example.salon.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public List<Lancamento> findAll() {
        return lancamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> findById(@PathVariable Long id) {
        Optional<Lancamento> lancamento = lancamentoService.findById(id);
        return lancamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Lancamento create(@RequestBody Lancamento lancamento) {
        return lancamentoService.save(lancamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> update(@PathVariable Long id, @RequestBody Lancamento lancamentoDetails) {
        Optional<Lancamento> optionalLancamento = lancamentoService.findById(id);
        if (optionalLancamento.isPresent()) {
            Lancamento lancamento = optionalLancamento.get();
            lancamento.setColaborador(lancamentoDetails.getColaborador());
            lancamento.setServico(lancamentoDetails.getServico());
            lancamento.setData(lancamentoDetails.getData());
            lancamento.setHora(lancamentoDetails.getHora());
            lancamento.setNomeCliente(lancamentoDetails.getNomeCliente());
            lancamento.setContatoCliente(lancamentoDetails.getContatoCliente());
            lancamento.setStatusServico(lancamentoDetails.getStatusServico());
            lancamento.setMetodoPagamento(lancamentoDetails.getMetodoPagamento());
            return ResponseEntity.ok(lancamentoService.save(lancamento));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lancamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
