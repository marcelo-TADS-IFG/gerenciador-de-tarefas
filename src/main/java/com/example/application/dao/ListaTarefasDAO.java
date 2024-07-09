package com.example.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import com.example.application.models.ListaTarefas;
import com.example.application.models.CategoriaTarefa;
import com.example.application.models.Prioridade;
import com.example.application.models.Status;
import com.example.application.models.Responsavel;

public class ListaTarefasDAO {

    public boolean salvar(ListaTarefas listaTarefas) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String insert = "INSERT INTO lista_tarefas (data_tarefa, descricao_tarefa, observacao, id_prioridade, id_categoria, id_status, id_responsavel) values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(listaTarefas.getDataTarefa()));
            preparedStatement.setString(2, listaTarefas.getDescricaoTarefa());
            preparedStatement.setString(3, listaTarefas.getObservacao());
            preparedStatement.setInt(4, listaTarefas.getPrioridade().getId());
            preparedStatement.setInt(5, listaTarefas.getCategoriaTarefa().getId());
            preparedStatement.setInt(6, listaTarefas.getStatus().getId());
            preparedStatement.setInt(7, listaTarefas.getResponsavel().getId());
            int resultado = preparedStatement.executeUpdate();
            return resultado > 0;

        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao salvar. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return false;
        }
    }

    public boolean alterar(ListaTarefas listaTarefas) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String alterar = "UPDATE lista_tarefas SET data_tarefa = ?, descricao_tarefa = ?, observacao = ?, id_prioridade = ?, id_categoria = ?, id_status = ?, id_responsavel = ? WHERE id_tarefa = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(alterar);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(listaTarefas.getDataTarefa()));
            preparedStatement.setString(2, listaTarefas.getDescricaoTarefa());
            preparedStatement.setString(3, listaTarefas.getObservacao());
            preparedStatement.setInt(4, listaTarefas.getPrioridade().getId());
            preparedStatement.setInt(5, listaTarefas.getCategoriaTarefa().getId());
            preparedStatement.setInt(6, listaTarefas.getStatus().getId());
            preparedStatement.setInt(7, listaTarefas.getResponsavel().getId());
            preparedStatement.setInt(8, listaTarefas.getId());
            int resultado = preparedStatement.executeUpdate();
            return resultado > 0;

        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao alterar. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return false;
        }
    }

    public boolean deletar(int idTarefa) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String delete = "DELETE FROM lista_tarefas WHERE id_tarefa = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, idTarefa);
            int resultado = preparedStatement.executeUpdate();
            return resultado > 0;

        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao deletar. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return false;
        }
    }

    public ListaTarefas buscarTarefa(int id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String busca = "SELECT * FROM lista_tarefas WHERE id_tarefa = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ListaTarefas listaTarefas = new ListaTarefas(
                        resultSet.getInt("id_tarefa"),
                        resultSet.getTimestamp("data_tarefa").toLocalDateTime(),
                        resultSet.getString("descricao_tarefa"),
                        resultSet.getString("observacao"),
                        new Prioridade(resultSet.getInt("id_prioridade")),
                        new CategoriaTarefa(resultSet.getInt("id_categoria")),
                        new Status(resultSet.getInt("id_status")),
                        new Responsavel(resultSet.getInt("id_responsavel"))
                );
                return listaTarefas;
            }

            return null;

        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao buscar. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return null;
        }
    }

    public List<ListaTarefas> buscarTodasTarefas() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String busca = "SELECT * FROM lista_tarefas";
            List<ListaTarefas> lista = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ListaTarefas listaTarefas = new ListaTarefas(
                        resultSet.getInt("id_tarefa"),
                        resultSet.getTimestamp("data_tarefa").toLocalDateTime(),
                        resultSet.getString("descricao_tarefa"),
                        resultSet.getString("observacao"),
                        new Prioridade(resultSet.getInt("id_prioridade")),
                        new CategoriaTarefa(resultSet.getInt("id_categoria")),
                        new Status(resultSet.getInt("id_status")),
                        new Responsavel(resultSet.getInt("id_responsavel"))
                );
                lista.add(listaTarefas);
            }

            return lista;
        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao buscar todos. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return null;
        }
    }
}
