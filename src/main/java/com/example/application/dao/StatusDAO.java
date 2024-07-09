package com.example.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import com.example.application.models.Status;

public class StatusDAO {

    public boolean salvar(Status status) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO stattus (descricao) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, status.getDescricao());
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

    public boolean alterar(Status status) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String alterar = "UPDATE stattus SET descricao = ? WHERE id_status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(alterar);
            preparedStatement.setString(1, status.getDescricao());
            preparedStatement.setInt(2, status.getId());
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

    public boolean deletar(int idStatus) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String delete = "DELETE FROM stattus WHERE id_status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, idStatus);
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

    public Status buscarStatus(int id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String busca = "SELECT * FROM stattus WHERE id_status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Status(resultSet.getInt("id_status"), resultSet.getString("descricao"));
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

    public List<Status> buscarTodos() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String busca = "SELECT * FROM stattus";
            List<Status> lista = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(busca);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lista.add(new Status(resultSet.getInt("id_status"), resultSet.getString("descricao")));
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
