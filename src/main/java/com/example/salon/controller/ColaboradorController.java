package com.example.salon.controller;

import com.example.salon.entity.Colaborador;
import com.example.salon.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Colaborador create(@RequestBody Colaborador colaborador) {
        return colaboradorService.save(colaborador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> update(@PathVariable Long id, @RequestBody Colaborador colaboradorDetails) {
        Optional<Colaborador> optionalColaborador = colaboradorService.findById(id);
        if (optionalColaborador.isPresent()) {
            Colaborador colaborador = optionalColaborador.get();
            colaborador.setNome(colaboradorDetails.getNome());
            colaborador.setCpf(colaboradorDetails.getCpf());
            colaborador.setPercentualComissao(colaboradorDetails.getPercentualComissao());
            colaborador.setChavePix(colaboradorDetails.getChavePix());
            colaborador.setDtInicio(colaboradorDetails.getDtInicio());
            colaborador.setDtFim(colaboradorDetails.getDtFim());
            return ResponseEntity.ok(colaboradorService.save(colaborador));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        colaboradorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
