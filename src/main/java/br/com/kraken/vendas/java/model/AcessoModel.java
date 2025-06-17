package br.com.kraken.vendas.java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "acesso")
public class AcessoModel {
    @Id
    private String usuarioId;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;
    @Column(name = "rules")
    private Rules rules;

    public AcessoModel(String usuarioId, String usuario, LocalDateTime dataCadastro, Rules rules) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.dataCadastro = dataCadastro;
        this.rules = rules;
    }

    public  AcessoModel(){}

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }
}
