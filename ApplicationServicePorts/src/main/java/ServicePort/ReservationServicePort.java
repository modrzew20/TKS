package ServicePort;

import model.Reservation;
import modelView.ReservationView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationServicePort {
    List<ReservationView> readAllReservation();

    ReservationView addReservation(UUID clientsUUID, UUID UUIDLane, LocalDateTime start, LocalDateTime end);

    ReservationView readOneReservation(UUID uuid);

    List<ReservationView> pastClientReservations(UUID clientsUUID);

    List<ReservationView> presentClientReservations(UUID clientsUUID);

    List<ReservationView> pastLaneReservations(UUID UUIDLane);

    List<ReservationView> presentLaneReservations(UUID UUIDLane);

    ReservationView endReservation(UUID uuid, LocalDateTime localDateTime);

    ReservationView delete(UUID uuid);
}
