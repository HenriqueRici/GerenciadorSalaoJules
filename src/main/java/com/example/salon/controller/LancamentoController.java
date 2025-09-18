package com.example.salon.controller;

import com.example.salon.entity.Lancamento;
import com.example.salon.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Lancamento create(@Valid @RequestBody Lancamento lancamento) {
        return lancamentoService.save(lancamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> update(@PathVariable Long id, @Valid @RequestBody Lancamento lancamentoDetails) {
        lancamentoDetails.setId(id);
        Lancamento updatedLancamento = lancamentoService.save(lancamentoDetails);
        return ResponseEntity.ok(updatedLancamento);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Lancamento> partialUpdate(@PathVariable Long id, @RequestBody Lancamento lancamentoDetails) {
        Lancamento updatedLancamento = lancamentoService.partialUpdate(id, lancamentoDetails);
        return ResponseEntity.ok(updatedLancamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lancamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
