package com.example.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import com.example.application.models.Responsavel;

public class ResponsavelDAO {

    public boolean salvar(Responsavel responsavel) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO responsavel (nome) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, responsavel.getNome());
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

    public boolean alterar(Responsavel responsavel) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String alterar = "UPDATE responsavel SET nome = ? WHERE id_responsavel = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(alterar);
            preparedStatement.setString(1, responsavel.getNome());
            preparedStatement.setInt(2, responsavel.getId());
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

    public boolean deletar(int idResponsavel) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String delete = "DELETE FROM responsavel WHERE id_responsavel = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, idResponsavel);
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

    public Responsavel buscarResponsavel(int id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String busca = "SELECT * FROM responsavel WHERE id_responsavel = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Responsavel(resultSet.getInt("id_responsavel"), resultSet.getString("nome"));
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

    public List<Responsavel> buscarTodos() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String busca = "SELECT * FROM responsavel";
            List<Responsavel> lista = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lista.add(new Responsavel(resultSet.getInt("id_responsavel"), resultSet.getString("nome")));
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
