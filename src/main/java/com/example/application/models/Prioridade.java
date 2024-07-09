package com.example.application.models;

public class Prioridade {
    private int id;
    private String descricao;
    
    public Prioridade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Prioridade(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
