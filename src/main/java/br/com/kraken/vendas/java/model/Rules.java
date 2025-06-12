package br.com.kraken.vendas.java.model;

public enum Rules {
    ADMIN ("admin"),
    SALES ("sales"),
    BUYER ("buyer");

    private String rule;
    Rules (String rule){
        this.rule = rule;
    }
}
