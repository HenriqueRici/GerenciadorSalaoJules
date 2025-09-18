package com.example.salon.service;

import com.example.salon.entity.Servico;
import com.example.salon.exception.ValidationException;
import com.example.salon.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> findById(Long id) {
        return servicoRepository.findById(id);
    }

    public Servico save(Servico servico) {
        List<Servico> existing = servicoRepository.findByTipoServicoAndOverlappingDateRange(
                servico.getTipoServico(),
                servico.getDtInicio(),
                servico.getDtFim());

        // When updating, filter out the service being updated
        if (servico.getId() != null) {
            existing = existing.stream()
                    .filter(s -> !s.getId().equals(servico.getId()))
                    .collect(Collectors.toList());
        }

        if (!existing.isEmpty()) {
            throw new ValidationException("Já existe um serviço com este tipo no período informado.");
        }

        return servicoRepository.save(servico);
    }

    public void deleteById(Long id) {
        servicoRepository.deleteById(id);
    }
}
