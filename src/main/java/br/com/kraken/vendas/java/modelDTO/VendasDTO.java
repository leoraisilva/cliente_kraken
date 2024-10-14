package br.com.kraken.vendas.java.modelDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record VendasDTO(List<UUID> produtos, List<Integer> quantidade, List<Long> posicao_id, Long idUsuario, float valorTotal, LocalDateTime dataVenda, char statusVendas) {
}
