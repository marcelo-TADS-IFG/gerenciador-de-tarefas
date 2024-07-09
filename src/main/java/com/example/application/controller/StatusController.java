package com.example.application.controller;

import java.util.List;

import com.example.application.dao.StatusDAO;
import com.example.application.models.Status;

public class StatusController {

    private StatusDAO statusDAO;

    public StatusController() {
        statusDAO = new StatusDAO();
    }

    public boolean salvarStatus(String descricao) {
        Status status = new Status(0, descricao); // ID será gerado automaticamente pelo banco de dados
        return statusDAO.salvar(status);
    }

    public boolean alterarStatus(int id, String descricao) {
        Status status = new Status(id, descricao);
        return statusDAO.alterar(status);
    }

    public boolean deletarStatus(int id) {
        return statusDAO.deletar(id);
    }

    public Status buscarStatus(int id) {
        return statusDAO.buscarStatus(id);
    }

    public List<Status> buscarTodosStatus() {
        return statusDAO.buscarTodos();
    }
}
