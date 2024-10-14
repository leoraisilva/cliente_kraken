package br.com.kraken.vendas.java.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "vendas")
public class VendasModel {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID idTransacao;
    @Column (name = "produtos", nullable = false)
    private List<UUID> produtos;
    @Column (name = "quantidade", nullable = false)
    private List<Integer> quantidade;
    @Column (name = "posicao_id", nullable = false)
    private List<Long> posicao_id;
    @Column (name = "id_usuario", nullable = false)
    private Long idUsuario;
    @Column (name = "valor_total")
    private float valorTotal;
    @Column (name = "data_venda")
    private LocalDateTime dataVenda;
    @Column (name = "status_venda", nullable = false)
    private char statusVendas;


    public VendasModel(List<UUID> produtos, List<Integer> quantidade, List<Long> posicao_id, Long idUsuario, float valorTotal, LocalDateTime dataVenda, char statusVendas) {
        this.produtos = produtos;
        this.quantidade = quantidade;
        this.posicao_id = posicao_id;
        this.idUsuario = idUsuario;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
        this.statusVendas = statusVendas;
    }
    public VendasModel(){}

    public UUID getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(UUID idTransacao) {
        this.idTransacao = idTransacao;
    }

    public List<UUID> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<UUID> produtos) {
        this.produtos = produtos;
    }

    public List<Integer> getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(List<Integer> quantidade) {
        this.quantidade = quantidade;
    }

    public List<Long> getPosicao_id() {
        return posicao_id;
    }

    public void setPosicao_id(List<Long> posicao_id) {
        this.posicao_id = posicao_id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public char getStatusVendas() {
        return statusVendas;
    }

    public void setStatusVendas(char statusVendas) {
        this.statusVendas = statusVendas;
    }
}
