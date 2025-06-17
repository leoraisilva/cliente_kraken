package br.com.kraken.vendas.java.service;

import br.com.kraken.vendas.java.repository.interfaces.iAcessoRepository;
import org.springframework.stereotype.Service;

@Service
public class AcessoService  {
    private final iAcessoRepository repository;

    public AcessoService(iAcessoRepository repository) {
        this.repository = repository;
    }

    public iAcessoRepository getRepository() {
        return repository;
    }
}
