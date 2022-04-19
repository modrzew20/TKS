package ServicePort;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationServicePort<T> {
    List<T> readAllReservation();

    T addReservation(UUID clientsUUID, UUID laneUUID, LocalDateTime start, LocalDateTime end);

    T readOneReservation(UUID uuid);

    List<T> pastClientReservations(UUID clientsUUID);

    List<T> presentClientReservations(UUID clientsUUID);

    List<T> pastLaneReservations(UUID laneUUID);

    List<T> presentLaneReservations(UUID laneUUID);

    T endReservation(UUID uuid, LocalDateTime localDateTime);

    T delete(UUID uuid);
}
