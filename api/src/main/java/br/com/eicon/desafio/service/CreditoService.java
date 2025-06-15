package br.com.eicon.desafio.service;

import br.com.eicon.desafio.entity.Credito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.eicon.desafio.repository.CreditoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository repository;

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        return repository.findByNumeroNfse(numeroNfse);
    }

    public Credito findByNumeroCredito(String numeroCredito) {

        Optional<Credito> optional = repository.findByNumeroCredito(numeroCredito);

        return optional.orElse(null);
    }

}
