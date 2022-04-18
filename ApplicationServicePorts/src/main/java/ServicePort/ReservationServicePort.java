package ServicePort;

import model.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationServicePort {
    List<Reservation> readAllReservation();

    Reservation addReservation(UUID clientsUUID, UUID UUIDLane, LocalDateTime start, LocalDateTime end);

    Reservation readOneReservation(UUID uuid);

    List<Reservation> pastClientReservations(UUID clientsUUID);

    List<Reservation> presentClientReservations(UUID clientsUUID);

    List<Reservation> pastLaneReservations(UUID UUIDLane);

    List<Reservation> presentLaneReservations(UUID UUIDLane);

    Reservation endReservation(UUID uuid, LocalDateTime localDateTime);

    Reservation delete(UUID uuid);
}
