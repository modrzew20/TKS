package adapters;

import Port.In.ReservationPortIn;
import com.example.kregielniaspring.model.Reservation;
import repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationPortInAdapter implements ReservationPortIn {

    ReservationAdapter reservationAdapter = new ReservationAdapter();
    ReservationRepository reservationRepository = new ReservationRepository();

    @Override
    public Reservation create(Reservation reservation) {
        return reservationAdapter.convertToReservation(reservationRepository.create(reservationAdapter.convertFromReservation(reservation)));
    }

    @Override
    public Reservation delete(UUID uuid) {
        return reservationAdapter.convertToReservation(reservationRepository.delete(uuid));
    }

    @Override
    public Reservation update(Reservation reservation) {
        return reservationAdapter.convertToReservation(reservationRepository.update(reservationAdapter.convertFromReservation(reservation)));
    }

    @Override
    public Reservation endReservation(UUID uuid, LocalDateTime localDateTime) {
        return reservationAdapter.convertToReservation(reservationRepository.endReservation(uuid, localDateTime));
    }

    @Override
    public boolean reservedLine(UUID uuid, LocalDateTime start, LocalDateTime end) {
        return reservationRepository.reservedLine(uuid, start, end);
    }
}
