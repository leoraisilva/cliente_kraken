package br.com.kraken.vendas.java.model;

public enum Rules {
    ADMIN ("admin"),
    USER ("user");

    private String rule;
    Rules (String rule){
        this.rule = rule;
    }
}
