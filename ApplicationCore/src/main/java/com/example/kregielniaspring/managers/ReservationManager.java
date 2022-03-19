package com.example.kregielniaspring.managers;


import com.example.kregielniaspring.model.LANE_TYPE;
import com.example.kregielniaspring.model.Lane;
import com.example.kregielniaspring.model.Reservation;
import com.example.kregielniaspring.model.User;
import com.example.kregielniaspring.repository.LaneRepository;
import com.example.kregielniaspring.repository.ReservationRepository;
import com.example.kregielniaspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationManager {


    private final LaneRepository laneRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository ;

    private Object lock = new Object();

    public List<Reservation> readAllReservation() {
        synchronized(lock) {
            return reservationRepository.readAll();
        }
    }

    public Reservation addReservation(UUID clientsUUID, UUID UUIDLane, LocalDateTime start, LocalDateTime end ) {

        Lane lane = laneRepository.readById(UUIDLane);
        User client = userRepository.readById(clientsUUID);
        if (!userRepository.readById(clientsUUID).getActive() || reservationRepository.reservedLine(UUIDLane, start, end) || lane == null || client == null)
            return null;
        synchronized(lock) {
            Reservation reservation = new Reservation(UUID.randomUUID(), lane, client, start, end);
            reservationRepository.create(reservation);
            return reservation;
        }
    }

    public Reservation readOneReservation(UUID uuid) {
        synchronized(lock) {
            return reservationRepository.readById(uuid);
        }
    }

    public List<Reservation> pastClientReservations(UUID clientsUUID) {
        synchronized(lock) {
            return reservationRepository.pastClientReservations(clientsUUID);
        }
    }

    public List<Reservation> presentClientReservations(UUID clientsUUID) {
        synchronized(lock) {
            return reservationRepository.presentClientReservations(clientsUUID);
        }
    }

    public List<Reservation> pastLaneReservations(UUID UUIDLane) {
        synchronized(lock) {
            return reservationRepository.pastLaneReservations(UUIDLane);
        }
    }

    public List<Reservation> presentLaneReservations(UUID UUIDLane) {
        synchronized(lock) {
            return reservationRepository.presentLaneReservations(UUIDLane);
        }
    }

    public Reservation endReservation ( UUID uuid, LocalDateTime localDateTime) {
        synchronized(lock) {
            return reservationRepository.endReservation(uuid, localDateTime);
        }
    }

    public Reservation delete(UUID uuid) {
        synchronized(lock) {
            return reservationRepository.delete(uuid);
        }
    }

}
