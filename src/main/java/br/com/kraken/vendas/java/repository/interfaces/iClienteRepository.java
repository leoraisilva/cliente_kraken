package br.com.kraken.vendas.java.repository.interfaces;

import br.com.kraken.vendas.java.model.ClienteModel;
import org.springframework.data.repository.CrudRepository;
    import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface iClienteRepository extends CrudRepository<ClienteModel, UUID> {
    UserDetails findByUsuario(String Usuario);
}
