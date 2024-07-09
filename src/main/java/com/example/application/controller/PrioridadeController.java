package com.example.application.controller;

import java.util.List;

import com.example.application.dao.PrioridadeDAO;
import com.example.application.models.Prioridade;

public class PrioridadeController {

    private PrioridadeDAO prioridadeDAO;

    public PrioridadeController() {
        prioridadeDAO = new PrioridadeDAO();
    }

    public boolean salvarPrioridade(String descricao) {
        Prioridade prioridade = new Prioridade(0, descricao); // ID será gerado automaticamente pelo banco de dados
        return prioridadeDAO.salvar(prioridade);
    }

    public boolean alterarPrioridade(int id, String descricao) {
        Prioridade prioridade = new Prioridade(id, descricao);
        return prioridadeDAO.alterar(prioridade);
    }

    public boolean alterarPrioridade3(Prioridade prioridade) {
        return prioridadeDAO.alterar(prioridade);
    }

    public boolean deletarPrioridade(int id) {
        return prioridadeDAO.deletar(id);
    }

    public Prioridade buscarPrioridade(int id) {
        return prioridadeDAO.buscarPrioridade(id);
    }

    public List<Prioridade> buscarTodasPrioridades() {
        return prioridadeDAO.buscarTodas();
    }
}
