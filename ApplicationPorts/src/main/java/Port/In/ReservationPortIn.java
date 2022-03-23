package Port.In;

import model.Reservation;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ReservationPortIn {

    Reservation create(Reservation reservation);

    Reservation delete(UUID uuid);

    Reservation update(Reservation reservation);

    Reservation endReservation(UUID uuid, LocalDateTime localDateTime);

    boolean reservedLine(UUID uuid, LocalDateTime start, LocalDateTime end);
}
