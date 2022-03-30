package adapters;

import Port.In.CreateReservationPort;
import Port.In.DeleteReservationPort;
import Port.In.UpdateReservationPort;
import model.Reservation;
import repository.ReservationRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import static aggregates.ReservationAdapter.convertFromReservation;
import static aggregates.ReservationAdapter.convertToReservation;

public class ReservationPortInAdapter implements CreateReservationPort, DeleteReservationPort, UpdateReservationPort {

    ReservationRepository reservationRepository = new ReservationRepository();

    @Override
    public Reservation create(Reservation reservation) {
        return convertToReservation(reservationRepository.create(convertFromReservation(reservation)));
    }

    @Override
    public Reservation delete(UUID uuid) {
        return convertToReservation(reservationRepository.delete(uuid));
    }

    @Override
    public Reservation update(Reservation reservation) {
        return convertToReservation(reservationRepository.update(convertFromReservation(reservation)));
    }

    @Override
    public Reservation endReservation(UUID uuid, LocalDateTime localDateTime) {
        return convertToReservation(reservationRepository.endReservation(uuid, localDateTime));
    }

    @Override
    public boolean reservedLine(UUID uuid, LocalDateTime start, LocalDateTime end) {
        return reservationRepository.reservedLine(uuid, start, end);
    }
}
