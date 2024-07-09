package com.example.application.controller;

import java.util.List;
import com.example.application.dao.CategoriaTarefaDAO;
import com.example.application.models.CategoriaTarefa;

public class CategoriaTarefaController {

    private CategoriaTarefaDAO categoriaTarefaDAO;

    public CategoriaTarefaController() {
        categoriaTarefaDAO = new CategoriaTarefaDAO();
    }

    public boolean salvarCategoriaTarefa(String descricao) {
        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(0, descricao); // ID será gerado automaticamente pelo banco de dados
        return categoriaTarefaDAO.salvar(categoriaTarefa);
    }

    public boolean alterarCategoriaTarefa(int id, String descricao) {
        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(id, descricao);
        return categoriaTarefaDAO.alterar(categoriaTarefa);
    }

    public boolean deletarCategoriaTarefa(int id) {
        return categoriaTarefaDAO.deletar(id);
    }

    public CategoriaTarefa buscarCategoriaTarefa(int id) {
        return categoriaTarefaDAO.buscarCategoriaTarefa(id);
    }

    public List<CategoriaTarefa> buscarTodasCategoriasTarefa() {
        return categoriaTarefaDAO.buscarTodas();
    }
}
