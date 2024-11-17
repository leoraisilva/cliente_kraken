package br.com.kraken.vendas.java.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "vendas")
public class ClienteModel {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID idCliente;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "nome")
    private String nome;
    @Column(name = "senha")
    private String senha;
    @Column(name = "email")
    private String email;
    @Column(name = "tell")
    private String tell;
    @Column(name = "cep")
    private String cep;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    public ClienteModel(UUID idCliente, String usuario, String nome, String senha, String email, String tell, String cep, LocalDateTime dataCadastro) {
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.tell = tell;
        this.cep = cep;
        this.dataCadastro = dataCadastro;
    }
    public ClienteModel () {}

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
