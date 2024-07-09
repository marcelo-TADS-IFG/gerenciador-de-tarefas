package com.example.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import com.example.application.models.Prioridade;

public class PrioridadeDAO {

    public boolean salvar(Prioridade prioridade) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO prioridade (descricao) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, prioridade.getDescricao());
            int resultado = preparedStatement.executeUpdate();
            return resultado > 0;
        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao salvar. Por favor, verifique a mensagem a seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return false;
        }
    }

    public boolean alterar(Prioridade prioridade) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String alterar = "UPDATE prioridade SET descricao = ? WHERE id_prioridade = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(alterar);
            preparedStatement.setString(1, prioridade.getDescricao());
            preparedStatement.setInt(2, prioridade.getId());
            int resultado = preparedStatement.executeUpdate();
            return resultado > 0;
        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao alterar. Por favor, verifique a mensagem a seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return false;
        }
    }

    public boolean deletar(int idPrioridade) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String delete = "DELETE FROM prioridade WHERE id_prioridade = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, idPrioridade);
            int resultado = preparedStatement.executeUpdate();
            return resultado > 0;
        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao deletar. Por favor, verifique a mensagem a seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return false;
        }
    }

    public Prioridade buscarPrioridade(int id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String busca = "SELECT * FROM prioridade WHERE id_prioridade = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Prioridade(resultSet.getInt("id_prioridade"), resultSet.getString("descricao"));
            }
            return null;
        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao buscar. Por favor, verifique a mensagem a seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return null;
        }
    }

    public List<Prioridade> buscarTodas() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String busca = "SELECT * FROM prioridade";
            List<Prioridade> lista = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lista.add(new Prioridade(resultSet.getInt("id_prioridade"), resultSet.getString("descricao")));
            }
            return lista;
        } catch (Exception e) {
            Notification notification = new Notification(
                    "Erro ao buscar todos. Por favor, verifique a mensagem a seguir: " + e.getMessage());
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return null;
        }
    }
}
