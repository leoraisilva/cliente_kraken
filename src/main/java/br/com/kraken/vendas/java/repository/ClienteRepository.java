package br.com.kraken.vendas.java.repository;

import br.com.kraken.vendas.java.model.ClienteModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteModel, UUID> {
}
