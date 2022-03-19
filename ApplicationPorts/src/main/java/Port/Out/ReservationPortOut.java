package Port.Out;

import com.example.kregielniaspring.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationPortOut {

    List<Reservation> readAll();

    Reservation readById(UUID uuid);

    List<Reservation> pastClientReservations(UUID clientsUUID);

    List<Reservation> presentClientReservations(UUID clientsUUID);

    List<Reservation> pastLaneReservations(UUID UUIDLane);

    List<Reservation> presentLaneReservations(UUID UUIDLane);
}
