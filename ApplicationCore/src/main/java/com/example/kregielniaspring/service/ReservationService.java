package com.example.kregielniaspring.service;


import Port.In.ReservationPortIn;
import Port.Out.LanePortOut;
import Port.Out.ReservationPortOut;
import Port.Out.UserPortOut;
import com.example.kregielniaspring.model.Lane;
import com.example.kregielniaspring.model.Reservation;
import com.example.kregielniaspring.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final Object lock = new Object();
    private ReservationPortOut reservationPortOut;
    private ReservationPortIn reservationPortIn;
    private LanePortOut lanePortOut;
    private UserPortOut userPort;

    public List<Reservation> readAllReservation() {
        synchronized (lock) {
            return reservationPortOut.readAll();
        }
    }

    public Reservation addReservation(UUID clientsUUID, UUID UUIDLane, LocalDateTime start, LocalDateTime end) {

        Lane lane = lanePortOut.readById(UUIDLane);
        User client = userPort.readById(clientsUUID);
        if (!userPort.readById(clientsUUID).getActive() || reservationPortIn.reservedLine(UUIDLane, start, end) || lane == null || client == null)
            return null;
        synchronized (lock) {
            Reservation reservation = new Reservation(UUID.randomUUID(), lane, client, start, end);
            reservationPortIn.create(reservation);
            return reservation;
        }
    }

    public Reservation readOneReservation(UUID uuid) {
        synchronized (lock) {
            return reservationPortOut.readById(uuid);
        }
    }

    public List<Reservation> pastClientReservations(UUID clientsUUID) {
        synchronized (lock) {
            return reservationPortOut.pastClientReservations(clientsUUID);
        }
    }

    public List<Reservation> presentClientReservations(UUID clientsUUID) {
        synchronized (lock) {
            return reservationPortOut.presentClientReservations(clientsUUID);
        }
    }

    public List<Reservation> pastLaneReservations(UUID UUIDLane) {
        synchronized (lock) {
            return reservationPortOut.pastLaneReservations(UUIDLane);
        }
    }

    public List<Reservation> presentLaneReservations(UUID UUIDLane) {
        synchronized (lock) {
            return reservationPortOut.presentLaneReservations(UUIDLane);
        }
    }

    public Reservation endReservation(UUID uuid, LocalDateTime localDateTime) {
        synchronized (lock) {
            return reservationPortIn.endReservation(uuid, localDateTime);
        }
    }

    public Reservation delete(UUID uuid) {
        synchronized (lock) {
            return reservationPortIn.delete(uuid);
        }
    }

}
