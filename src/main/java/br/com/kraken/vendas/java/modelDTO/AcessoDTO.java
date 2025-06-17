package br.com.kraken.vendas.java.modelDTO;

import br.com.kraken.vendas.java.model.Rules;

import java.time.LocalDateTime;

public record AcessoDTO(String usuarioId, String usuario, LocalDateTime dataCadastro, Rules rules) {
}
