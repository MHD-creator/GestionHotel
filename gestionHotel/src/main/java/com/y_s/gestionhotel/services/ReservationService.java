package com.y_s.gestionhotel.services;

import com.y_s.gestionhotel.entites.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private Connection connection;

    public ReservationService(Connection connection) {
        this.connection = connection;
    }

    public List<Reservation> fetchReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT reservation_id, client_name, room_reserved, duration FROM reservations";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                reservations.add(new Reservation(rs.getInt("reservation_id"), rs.getString("client_name"), rs.getString("room_reserved"), rs.getInt("duration")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public void addReservation(String clientName, String roomReserved, int duration) {
        String query = "INSERT INTO reservations (client_name, room_reserved, duration) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, clientName);
            pstmt.setString(2, roomReserved);
            pstmt.setInt(3, duration);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
