package br.com.kraken.vendas.java.service;

import br.com.kraken.vendas.java.repository.interfaces.iClienteRepository;
import br.com.kraken.vendas.java.service.interfaces.iClienteService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements iClienteService, UserDetailsService {
    private final iClienteRepository repository;

    public ClienteService(iClienteRepository repository) {
        this.repository = repository;
    }

    public iClienteRepository getClienteRepository() {
        return repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsuario(username);
    }
}
