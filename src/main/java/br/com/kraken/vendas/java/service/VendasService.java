package br.com.kraken.vendas.java.service;

import br.com.kraken.vendas.java.repository.VendasRepository;
import org.springframework.stereotype.Service;

@Service
public class VendasService {
    private final VendasRepository vendasRepository;

    public VendasService (VendasRepository vendasRepository) {
        this.vendasRepository = vendasRepository;
    }

    public VendasRepository getVendasRepository() {
        return vendasRepository;
    }
}
