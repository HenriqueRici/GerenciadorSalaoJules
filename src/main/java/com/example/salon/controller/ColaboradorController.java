package com.example.salon.controller;

import com.example.salon.entity.Colaborador;
import com.example.salon.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public List<Colaborador> findAll() {
        return colaboradorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> findById(@PathVariable Long id) {
        Optional<Colaborador> colaborador = colaboradorService.findById(id);
        return colaborador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Colaborador create(@Valid @RequestBody Colaborador colaborador) {
        return colaboradorService.save(colaborador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> update(@PathVariable Long id, @Valid @RequestBody Colaborador colaboradorDetails) {
        // Ensure the ID from the path is set on the object
        colaboradorDetails.setId(id);
        Colaborador updatedColaborador = colaboradorService.save(colaboradorDetails);
        return ResponseEntity.ok(updatedColaborador);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Colaborador> partialUpdate(@PathVariable Long id, @RequestBody Colaborador colaboradorDetails) {
        Colaborador updatedColaborador = colaboradorService.partialUpdate(id, colaboradorDetails);
        return ResponseEntity.ok(updatedColaborador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        colaboradorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
