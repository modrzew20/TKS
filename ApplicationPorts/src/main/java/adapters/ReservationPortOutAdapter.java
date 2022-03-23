package adapters;

import Port.Out.ReservationPortOut;
import model.Reservation;
import repository.ReservationRepository;

import java.util.List;
import java.util.UUID;

public class ReservationPortOutAdapter implements ReservationPortOut {

    ReservationAdapter reservationAdapter = new ReservationAdapter();
    ReservationRepository reservationRepository = new ReservationRepository();

    @Override
    public List<Reservation> readAll() {
        return reservationAdapter.convertToListReservation(reservationRepository.readAll());
    }

    @Override
    public Reservation readById(UUID uuid) {
        return reservationAdapter.convertToReservation(reservationRepository.readById(uuid));
    }

    @Override
    public List<Reservation> pastClientReservations(UUID clientsUUID) {
        return reservationAdapter.convertToListReservation(reservationRepository.pastClientReservations(clientsUUID));
    }

    @Override
    public List<Reservation> presentClientReservations(UUID clientsUUID) {
        return reservationAdapter.convertToListReservation(reservationRepository.presentClientReservations(clientsUUID));
    }

    @Override
    public List<Reservation> pastLaneReservations(UUID UUIDLane) {
        return reservationAdapter.convertToListReservation(reservationRepository.pastLaneReservations(UUIDLane));
    }

    @Override
    public List<Reservation> presentLaneReservations(UUID UUIDLane) {
        return reservationAdapter.convertToListReservation(reservationRepository.presentLaneReservations(UUIDLane));
    }
}
