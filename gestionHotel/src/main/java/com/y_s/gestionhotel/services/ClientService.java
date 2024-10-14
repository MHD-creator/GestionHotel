package com.y_s.gestionhotel.services;

import com.y_s.gestionhotel.entites.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private Connection connection;

    public ClientService(Connection connection) {
        this.connection = connection;
    }

    public List<Client> fetchClients() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT client_id, client_name FROM clients";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                clients.add(new Client(rs.getInt("client_id"), rs.getString("client_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void addClient(String clientName) {
        String query = "INSERT INTO clients (client_name) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, clientName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
