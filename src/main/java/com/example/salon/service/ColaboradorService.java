package com.example.salon.service;

import com.example.salon.entity.Colaborador;
import com.example.salon.exception.ValidationException;
import com.example.salon.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }

    public Optional<Colaborador> findById(Long id) {
        return colaboradorRepository.findById(id);
    }

    public Colaborador save(Colaborador colaborador) {
        List<Colaborador> existing = colaboradorRepository.findByCpfAndOverlappingDateRange(
                colaborador.getCpf(),
                colaborador.getDtInicio(),
                colaborador.getDtFim());

        // When updating, filter out the collaborator being updated
        if (colaborador.getId() != null) {
            existing = existing.stream()
                    .filter(c -> !c.getId().equals(colaborador.getId()))
                    .collect(Collectors.toList());
        }

        if (!existing.isEmpty()) {
            throw new ValidationException("Já existe um colaborador com este CPF no período informado.");
        }

        return colaboradorRepository.save(colaborador);
    }

    public void deleteById(Long id) {
        colaboradorRepository.deleteById(id);
    }
}
