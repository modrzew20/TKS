package repository;


import modelEnt.ReservationEnt;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ReservationRepository implements RepositoryInterface<ReservationEnt> {

    private final List<ReservationEnt> reservationList = new ArrayList<>();

    private static boolean checkIfExists(List<ReservationEnt> list, UUID uuid) {
        return list.stream().anyMatch(r -> r.getUuid().equals(uuid));
    }

    @Override
    public List<ReservationEnt> readAll() {
        return reservationList;
    }

    @Override
    public ReservationEnt readById(UUID uuid) {
        return reservationList.stream().filter(reservation1 -> uuid.equals(reservation1.getUuid())).findFirst().orElse(null);
    }

    @Override
    public ReservationEnt create(ReservationEnt object) {
        UUID uuid = UUID.randomUUID();
        while (checkIfExists(reservationList, uuid)) {
            uuid = UUID.randomUUID();
        }
        object.setUuid(uuid);
        reservationList.add(object);
        return object;
    }

    @Override
    public ReservationEnt delete(UUID uuid) {
        LocalDateTime time = LocalDateTime.now();
        for (int i = 0; i < reservationList.size(); i++) {
            if (reservationList.get(i).getUuid().equals(uuid)
                    && (reservationList.get(i).getEndReservation().isBefore(time)
                    || reservationList.get(i).getEndReservation() == null)) {
                ReservationEnt reservation = reservationList.get(i);
                reservationList.remove(i);
                return reservation;
            }
        }
        return null;
    }

    @Override
    public ReservationEnt update(ReservationEnt object) {
        return null;
    }

    public List<ReservationEnt> pastClientReservations(UUID clientsUUID) {
        List<ReservationEnt> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for (ReservationEnt reservation : reservationList) {
            if (reservation.getClient().getUuid().equals(clientsUUID)
                    && reservation.getEndReservation().isBefore(time)) list.add(reservation);
        }
        return list;
    }

    public List<ReservationEnt> presentClientReservations(UUID clientsUUID) {
        List<ReservationEnt> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for (ReservationEnt reservation : reservationList) {
            if (reservation.getClient().getUuid().equals(clientsUUID)
                    && (reservation.getEndReservation().isAfter(time)
                    || reservation.getEndReservation() == null)) list.add(reservation);
        }
        return list;
    }

    public List<ReservationEnt> pastLaneReservations(UUID UUIDLane) {
        List<ReservationEnt> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for (ReservationEnt reservation : reservationList) {
            if (reservation.getLane().getUuid().equals(UUIDLane)
                    && reservation.getEndReservation().isBefore(time)) list.add(reservation);
        }
        return list;
    }

    public List<ReservationEnt> presentLaneReservations(UUID UUIDLane) {
        List<ReservationEnt> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();
        for (ReservationEnt reservation : reservationList) {
            if (reservation.getLane().getUuid().equals(UUIDLane)
                    && (reservation.getEndReservation().isAfter(time)
                    || reservation.getEndReservation() == null)) list.add(reservation);
        }
        return list;
    }

    public ReservationEnt endReservation(UUID uuid, LocalDateTime localDateTime) {
        for (ReservationEnt value : reservationList) {
            if (value.getUuid().equals(uuid)) {
                value.setEndReservation(localDateTime);
                return value;
            }
        }
        return null;
    }

    public boolean reservedLine(UUID uuid, LocalDateTime start, LocalDateTime end) {
        for (ReservationEnt reservation : reservationList) {
            if (reservation.getLane().getUuid().equals(uuid)
                    && (reservation.getEndReservation().isAfter(start)
                    && reservation.getEndReservation().isBefore(end))) return true;
            if (reservation.getLane().getUuid().equals(uuid)
                    && (reservation.getStartReservation().isAfter(start)
                    && reservation.getStartReservation().isBefore(end)
                    && reservation.getEndReservation().isAfter(start)
                    && reservation.getEndReservation().isBefore(end))) return true;
            if (reservation.getLane().getUuid().equals(uuid)
                    && (reservation.getStartReservation().isAfter(start)
                    && reservation.getStartReservation().isBefore(end))) return true;
            if (reservation.getLane().getUuid().equals(uuid)
                    && (reservation.getStartReservation().isBefore(start)
                    && reservation.getEndReservation().isAfter(end))) return true;
            if (reservation.getLane().getUuid().equals(uuid)
                    && (reservation.getStartReservation().isEqual(start)
                    && reservation.getEndReservation().isEqual(end))) return true;
        }
        return false;
    }
}
