package com.example.kregielniaspring.repository;


import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ReservationRepository implements RepositoryInterface<Reservation> {

    private final List<Reservation> reservationList= new ArrayList<>();;

    @Override
    public List<Reservation> readAll() {
        return reservationList;
    }

    @Override
    public Reservation readById(UUID uuid) {
        return reservationList.stream().filter(reservation1 -> uuid.equals(reservation1.getUuid())).findFirst().orElse(null);
    }

    @Override
    public Reservation create(Reservation object) {
        UUID uuid = UUID.randomUUID();
        while (checkIfExists(reservationList, uuid)) {
            uuid = UUID.randomUUID();
        }
        object.setUuid(uuid);
        reservationList.add(object);
        return object;
    }

    @Override
    public Reservation delete(UUID uuid) {
        LocalDateTime time = LocalDateTime.now();
        for (int i = 0; i < reservationList.size(); i++) {
            if( reservationList.get(i).getUuid().equals(uuid)
                    && ( reservationList.get(i).getEndReservation().isBefore(time)
                    ||  reservationList.get(i).getEndReservation() == null)) {
                Reservation  reservation = reservationList.get(i);
                reservationList.remove(i);
                return reservation;
            }
        }
        return null;
    }

    @Override
    public Reservation update(Reservation object) {
        return null;
    }





    public List<Reservation> pastClientReservations(UUID clientsUUID) {
        List<Reservation> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for (Reservation reservation : reservationList) {
            if (reservation.getClient().getUuid().equals(clientsUUID)
                    && reservation.getEndReservation().isBefore(time)) list.add(reservation);
        }
        return list;
    }

    public List<Reservation> presentClientReservations(UUID clientsUUID) {
        List<Reservation> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for (Reservation reservation : reservationList) {
            if (reservation.getClient().getUuid().equals(clientsUUID)
                    && (reservation.getEndReservation().isAfter(time)
                    || reservation.getEndReservation() == null)) list.add(reservation);
        }
        return list;
    }


    public List<Reservation> pastLaneReservations(UUID UUIDLane) {
        List<Reservation> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for (Reservation reservation : reservationList) {
            if (reservation.getLane().getUuid().equals(UUIDLane)
                    && reservation.getEndReservation().isBefore(time)) list.add(reservation);
        }
        return list;
    }

    public List<Reservation> presentLaneReservations(UUID UUIDLane) {
        List<Reservation> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for (Reservation reservation : reservationList) {
            if (reservation.getLane().getUuid().equals(UUIDLane)
                    && (reservation.getEndReservation().isAfter(time)
                    || reservation.getEndReservation() == null)) list.add(reservation);
        }
        return list;
    }

    public Reservation endReservation ( UUID uuid, LocalDateTime localDateTime) {
        for (Reservation value : reservationList) {
            if (value.getUuid().equals(uuid)) {
                value.setEndReservation(localDateTime);
                return value;
            }
        }
        return null;
    }


    public boolean reservedLine(UUID uuidlane, LocalDateTime start, LocalDateTime end) {
        for (Reservation reservation : reservationList) {
            if (reservation.getLane().getUuid().equals(uuidlane)
                    && (reservation.getEndReservation().isAfter(start)
                    && reservation.getEndReservation().isBefore(end))) return true;
            if (reservation.getLane().getUuid().equals(uuidlane)
                    && (reservation.getStartReservation().isAfter(start)
                    && reservation.getStartReservation().isBefore(end)
                    && reservation.getEndReservation().isAfter(start)
                    && reservation.getEndReservation().isBefore(end))) return true;
            if (reservation.getLane().getUuid().equals(uuidlane)
                    && (reservation.getStartReservation().isAfter(start)
                    && reservation.getStartReservation().isBefore(end))) return true;
            if (reservation.getLane().getUuid().equals(uuidlane)
                    && (reservation.getStartReservation().isBefore(start)
                    && reservation.getEndReservation().isAfter(end))) return true;
            if (reservation.getLane().getUuid().equals(uuidlane)
                    && (reservation.getStartReservation().isEqual(start)
                    && reservation.getEndReservation().isEqual(end))) return true;
        }
        return false;
    }

    private static boolean checkIfExists(List<Reservation> list, UUID uuid) {
        return list.stream().anyMatch(reserv -> reserv.getUuid().equals(uuid));
    }
}
