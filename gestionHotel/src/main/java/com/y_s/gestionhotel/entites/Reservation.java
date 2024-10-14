package com.y_s.gestionhotel.entites;

public class Reservation {
    private int reservationId;
    private String clientName;
    private String roomReserved;
    private int duration;

    public Reservation(int reservationId, String clientName, String roomReserved, int duration) {
        this.reservationId = reservationId;
        this.clientName = clientName;
        this.roomReserved = roomReserved;
        this.duration = duration;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getRoomReserved() {
        return roomReserved;
    }

    public int getDuration() {
        return duration;
    }
}
