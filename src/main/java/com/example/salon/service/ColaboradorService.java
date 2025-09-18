package com.example.salon.service;

import com.example.salon.entity.Colaborador;
import com.example.salon.exception.ResourceNotFoundException;
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
        validateOverlappingCpf(colaborador);
        return colaboradorRepository.save(colaborador);
    }

    public Colaborador partialUpdate(Long id, Colaborador colaboradorDetails) {
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador não encontrado com o id: " + id));

        if (colaboradorDetails.getNome() != null) {
            colaborador.setNome(colaboradorDetails.getNome());
        }
        if (colaboradorDetails.getCpf() != null) {
            colaborador.setCpf(colaboradorDetails.getCpf());
        }
        if (colaboradorDetails.getPercentualComissao() != null) {
            colaborador.setPercentualComissao(colaboradorDetails.getPercentualComissao());
        }
        if (colaboradorDetails.getChavePix() != null) {
            colaborador.setChavePix(colaboradorDetails.getChavePix());
        }
        if (colaboradorDetails.getDtInicio() != null) {
            colaborador.setDtInicio(colaboradorDetails.getDtInicio());
        }
        if (colaboradorDetails.getDtFim() != null) {
            colaborador.setDtFim(colaboradorDetails.getDtFim());
        }

        validateOverlappingCpf(colaborador);
        return colaboradorRepository.save(colaborador);
    }

    public void deleteById(Long id) {
        colaboradorRepository.deleteById(id);
    }

    private void validateOverlappingCpf(Colaborador colaborador) {
        List<Colaborador> existing = colaboradorRepository.findByCpfAndOverlappingDateRange(
                colaborador.getCpf(),
                colaborador.getDtInicio(),
                colaborador.getDtFim());

        if (colaborador.getId() != null) {
            existing = existing.stream()
                    .filter(c -> !c.getId().equals(colaborador.getId()))
                    .collect(Collectors.toList());
        }

        if (!existing.isEmpty()) {
            throw new ValidationException("Já existe um colaborador com este CPF no período informado.");
        }
    }
}
