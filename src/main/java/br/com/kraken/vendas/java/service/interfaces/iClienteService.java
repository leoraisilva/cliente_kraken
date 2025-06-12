package br.com.kraken.vendas.java.service.interfaces;

import br.com.kraken.vendas.java.repository.interfaces.iClienteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface iClienteService {
    iClienteRepository getClienteRepository();
}
