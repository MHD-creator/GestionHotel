package com.y_s.gestionhotel.entites;

public class Chambre {
    private String roomNumber;
    private String roomType;
    private double roomPrice;

    public Chambre(String roomNumber, String roomType, double roomPrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }
}
