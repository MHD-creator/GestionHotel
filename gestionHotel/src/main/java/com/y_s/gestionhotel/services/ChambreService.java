package com.y_s.gestionhotel.services;

import com.y_s.gestionhotel.entites.Chambre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreService {

    private Connection connection;

    public ChambreService(Connection connection) {
        this.connection = connection;
    }
    
    public List<Chambre> fetchChambres() {
        List<Chambre> chambres = new ArrayList<>();
        String query = "SELECT room_number, room_type, room_price FROM rooms";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                chambres.add(new Chambre(rs.getString("room_number"), rs.getString("room_type"), rs.getDouble("room_price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chambres;
    }

    public void addChambre(String roomNumber, String roomType, double roomPrice) {
        String query = "INSERT INTO rooms (room_number, room_type, room_price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, roomNumber);
            pstmt.setString(2, roomType);
            pstmt.setDouble(3, roomPrice);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
