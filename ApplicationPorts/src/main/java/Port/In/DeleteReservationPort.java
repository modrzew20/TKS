package Port.In;

import model.Reservation;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DeleteReservationPort {
    Reservation delete(UUID uuid);
    Reservation endReservation(UUID uuid, LocalDateTime localDateTime);
}
