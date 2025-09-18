package com.example.salon.controller;

import com.example.salon.entity.Servico;
import com.example.salon.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public List<Servico> findAll() {
        return servicoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.findById(id);
        return servico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Servico create(@Valid @RequestBody Servico servico) {
        return servicoService.save(servico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> update(@PathVariable Long id, @Valid @RequestBody Servico servicoDetails) {
        servicoDetails.setId(id);
        Servico updatedServico = servicoService.save(servicoDetails);
        return ResponseEntity.ok(updatedServico);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Servico> partialUpdate(@PathVariable Long id, @RequestBody Servico servicoDetails) {
        Servico updatedServico = servicoService.partialUpdate(id, servicoDetails);
        return ResponseEntity.ok(updatedServico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
