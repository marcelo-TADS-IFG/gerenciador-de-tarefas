package com.example.application.controller;

import java.util.List;

import com.example.application.dao.ResponsavelDAO;
import com.example.application.models.Responsavel;

public class ResponsavelController {

    private ResponsavelDAO responsavelDAO;

    public ResponsavelController() {
        responsavelDAO = new ResponsavelDAO();
    }

    public boolean salvarResponsavel(String nome) {
        Responsavel responsavel = new Responsavel(0, nome); // ID será gerado automaticamente pelo banco de dados
        return responsavelDAO.salvar(responsavel);
    }

    public boolean alterarResponsavel(int id, String nome) {
        Responsavel responsavel = new Responsavel(id, nome);
        return responsavelDAO.alterar(responsavel);
    }

    public boolean deletarResponsavel(int id) {
        return responsavelDAO.deletar(id);
    }

    public Responsavel buscarResponsavel(int id) {
        return responsavelDAO.buscarResponsavel(id);
    }

    public List<Responsavel> buscarTodosResponsaveis() {
        return responsavelDAO.buscarTodos();
    }
}
