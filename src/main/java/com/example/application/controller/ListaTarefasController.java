package com.example.application.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.example.application.dao.ListaTarefasDAO;
import com.example.application.models.CategoriaTarefa;
import com.example.application.models.ListaTarefas;
import com.example.application.models.Prioridade;
import com.example.application.models.Responsavel;
import com.example.application.models.Status;

public class ListaTarefasController {

    private ListaTarefasDAO listaTarefasDAO;

    public ListaTarefasController() {
        listaTarefasDAO = new ListaTarefasDAO();
    }

    public boolean salvarTarefa(LocalDateTime dataTarefa, String descricaoTarefa, String observacao, Prioridade prioridade, CategoriaTarefa categoriaTarefa, Status status, Responsavel responsavel) {
        ListaTarefas tarefa = new ListaTarefas(0, dataTarefa, descricaoTarefa, observacao, prioridade, categoriaTarefa, status, responsavel);
        return listaTarefasDAO.salvar(tarefa);
    }

    public boolean alterarTarefa(int id, LocalDateTime dataTarefa, String descricaoTarefa, String observacao, Prioridade prioridade, CategoriaTarefa categoriaTarefa, Status status, Responsavel responsavel) {
        ListaTarefas tarefa = new ListaTarefas(id, dataTarefa, descricaoTarefa, observacao, prioridade, categoriaTarefa, status, responsavel);
        return listaTarefasDAO.alterar(tarefa);
    }

    public boolean deletarTarefa(int id) {
        return listaTarefasDAO.deletar(id);
    }

    public ListaTarefas buscarTarefa(int id) {
        return listaTarefasDAO.buscarTarefa(id);
    }

    public List<ListaTarefas> buscarTodasTarefas() {
        return listaTarefasDAO.buscarTodasTarefas();
    }
}
