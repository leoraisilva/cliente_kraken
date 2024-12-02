package br.com.kraken.vendas.java.service;

import br.com.kraken.vendas.java.model.ClienteModel;
import br.com.kraken.vendas.java.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }



}
