package br.com.kraken.vendas.java.modelDTO;

import br.com.kraken.vendas.java.model.Rules;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ClienteDTO(UUID idCliente, String usuario, String nome, String senha, String email, String tell, String cep, LocalDateTime dataCadastro, Rules rules) {
}
