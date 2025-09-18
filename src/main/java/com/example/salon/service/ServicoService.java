package com.example.salon.service;

import com.example.salon.entity.Servico;
import com.example.salon.exception.ResourceNotFoundException;
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
        validateOverlappingTipoServico(servico);
        return servicoRepository.save(servico);
    }

    public Servico partialUpdate(Long id, Servico servicoDetails) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o id: " + id));

        if (servicoDetails.getTipoServico() != null) {
            servico.setTipoServico(servicoDetails.getTipoServico());
        }
        if (servicoDetails.getTempoServico() != null) {
            servico.setTempoServico(servicoDetails.getTempoServico());
        }
        if (servicoDetails.getValor() != null) {
            servico.setValor(servicoDetails.getValor());
        }
        if (servicoDetails.getDtInicio() != null) {
            servico.setDtInicio(servicoDetails.getDtInicio());
        }
        if (servicoDetails.getDtFim() != null) {
            servico.setDtFim(servicoDetails.getDtFim());
        }

        validateOverlappingTipoServico(servico);
        return servicoRepository.save(servico);
    }


    public void deleteById(Long id) {
        servicoRepository.deleteById(id);
    }

    private void validateOverlappingTipoServico(Servico servico) {
        List<Servico> existing = servicoRepository.findByTipoServicoAndOverlappingDateRange(
                servico.getTipoServico(),
                servico.getDtInicio(),
                servico.getDtFim());

        if (servico.getId() != null) {
            existing = existing.stream()
                    .filter(s -> !s.getId().equals(servico.getId()))
                    .collect(Collectors.toList());
        }

        if (!existing.isEmpty()) {
            throw new ValidationException("Já existe um serviço com este tipo no período informado.");
        }
    }
}
