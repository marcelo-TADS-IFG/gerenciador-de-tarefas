package com.example.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import com.example.application.models.CategoriaTarefa;

public class CategoriaTarefaDAO {

    public boolean salvar(CategoriaTarefa categoriaTarefa) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String insert = "INSERT INTO categoria_tarefa (descricao) values (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, categoriaTarefa.getDescricao());
            int resultado = preparedStatement.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.print(e.getMessage());
            Notification notification = new Notification(
                    "Erro ao salvar. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return false;
        }
    }

    public boolean alterar(CategoriaTarefa categoriaTarefa) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String alterar = "UPDATE categoria_tarefa SET descricao = ? WHERE id_categoria = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(alterar);
            preparedStatement.setString(1, categoriaTarefa.getDescricao());
            preparedStatement.setInt(2, categoriaTarefa.getId());
            int resultado = preparedStatement.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            Notification notification = new Notification(
                    "Erro ao alterar. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return false;
        }
    }

    public boolean deletar(int idCategoria) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String delete = "DELETE FROM categoria_tarefa WHERE id_categoria = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, idCategoria);

            int resultado = preparedStatement.executeUpdate();
            return resultado > 0;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            Notification notification = new Notification(
                    "Erro ao deletar. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return false;
        }
    }

    public CategoriaTarefa buscarCategoriaTarefa(int id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String busca = "SELECT * FROM categoria_tarefa WHERE id_categoria = ?";

            CategoriaTarefa categoriaTarefa = null;

            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                categoriaTarefa = new CategoriaTarefa(resultSet.getInt("id_categoria"), resultSet.getString("descricao"));
            }

            return categoriaTarefa;

        } catch (Exception e) {
            System.out.print(e.getMessage());
            Notification notification = new Notification(
                    "Erro ao buscar. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return null;
        }
    }

    public List<CategoriaTarefa> buscarTodas() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String busca = "SELECT * FROM categoria_tarefa";
            List<CategoriaTarefa> lista = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CategoriaTarefa categoriaTarefa = new CategoriaTarefa(resultSet.getInt("id_categoria"), resultSet.getString("descricao"));
                lista.add(categoriaTarefa);
            }

            return lista;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            Notification notification = new Notification(
                    "Erro ao buscar todos. Por favor, verifique a mensagem à seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return null;
        }
    }
}
