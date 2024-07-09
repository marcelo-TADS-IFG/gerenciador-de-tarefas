package com.example.application.models;

public class Status {
    private int id;
    private String descricao;
    
    public Status(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Status(int id) {
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
