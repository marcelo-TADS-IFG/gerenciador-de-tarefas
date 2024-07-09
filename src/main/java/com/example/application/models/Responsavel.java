package com.example.application.models;

public class Responsavel {
    private int id;
    private String nome;

    public Responsavel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Responsavel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
  
}
