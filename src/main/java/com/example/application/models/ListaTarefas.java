package com.example.application.models;

import java.time.LocalDateTime;

public class ListaTarefas {
    private int id;
    private LocalDateTime dataTarefa;
    private String descricaoTarefa;
    private String observacao;
    private Prioridade prioridade;
    private CategoriaTarefa categoriaTarefa;
    private Status status;
    private Responsavel responsavel;

    public ListaTarefas(int id, LocalDateTime dataTarefa, String descricaoTarefa, String observacao,
            Prioridade prioridade, CategoriaTarefa categoriaTarefa, Status status, Responsavel responsavel) {
        this.id = id;
        this.dataTarefa = dataTarefa;
        this.descricaoTarefa = descricaoTarefa;
        this.observacao = observacao;
        this.prioridade = prioridade;
        this.categoriaTarefa = categoriaTarefa;
        this.status = status;
        this.responsavel = responsavel;
    }

    public ListaTarefas(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataTarefa() {
        return dataTarefa;
    }

    public void setDataTarefa(LocalDateTime dataTarefa) {
        this.dataTarefa = dataTarefa;
    }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public CategoriaTarefa getCategoriaTarefa() {
        return categoriaTarefa;
    }

    public void setCategoriaTarefa(CategoriaTarefa categoriaTarefa) {
        this.categoriaTarefa = categoriaTarefa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}
