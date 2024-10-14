package br.com.kraken.vendas.java.repository;

import br.com.kraken.vendas.java.model.VendasModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendasRepository extends CrudRepository<VendasModel, UUID> {
}
