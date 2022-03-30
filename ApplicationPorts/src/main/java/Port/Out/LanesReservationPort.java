package Port.Out;

import model.Reservation;

import java.util.List;
import java.util.UUID;

public interface LanesReservationPort {



    List<Reservation> pastLaneReservations(UUID UUIDLane);

    List<Reservation> presentLaneReservations(UUID UUIDLane);
}
