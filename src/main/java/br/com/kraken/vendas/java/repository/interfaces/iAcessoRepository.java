package br.com.kraken.vendas.java.repository.interfaces;

import br.com.kraken.vendas.java.model.AcessoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iAcessoRepository extends CrudRepository<AcessoModel, String > {
}
