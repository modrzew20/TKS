package ServicePort;

import modelView.ReservationView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationServicePort {
    List<ReservationView> readAllReservation();

    ReservationView addReservation(UUID clientsUUID, UUID laneUUID, LocalDateTime start, LocalDateTime end);

    ReservationView readOneReservation(UUID uuid);

    List<ReservationView> pastClientReservations(UUID clientsUUID);

    List<ReservationView> presentClientReservations(UUID clientsUUID);

    List<ReservationView> pastLaneReservations(UUID laneUUID);

    List<ReservationView> presentLaneReservations(UUID laneUUID);

    ReservationView endReservation(UUID uuid, LocalDateTime localDateTime);

    ReservationView delete(UUID uuid);
}
