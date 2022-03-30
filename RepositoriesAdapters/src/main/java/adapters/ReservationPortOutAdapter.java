package adapters;

import Port.Out.ClientsReservationPort;
import Port.Out.LanesReservationPort;
import Port.Out.ReadReservationPort;
import model.Reservation;
import repository.ReservationRepository;
import java.util.List;
import java.util.UUID;
import static aggregates.ReservationAdapter.convertToListReservation;
import static aggregates.ReservationAdapter.convertToReservation;


public class ReservationPortOutAdapter implements LanesReservationPort, ReadReservationPort, ClientsReservationPort {

    ReservationRepository reservationRepository = new ReservationRepository();

    @Override
    public List<Reservation> readAll() {
        return convertToListReservation(reservationRepository.readAll());
    }

    @Override
    public Reservation readById(UUID uuid) {
        return convertToReservation(reservationRepository.readById(uuid));
    }

    @Override
    public List<Reservation> pastClientReservations(UUID clientsUUID) {
        return convertToListReservation(reservationRepository.pastClientReservations(clientsUUID));
    }

    @Override
    public List<Reservation> presentClientReservations(UUID clientsUUID) {
        return convertToListReservation(reservationRepository.presentClientReservations(clientsUUID));
    }

    @Override
    public List<Reservation> pastLaneReservations(UUID UUIDLane) {
        return convertToListReservation(reservationRepository.pastLaneReservations(UUIDLane));
    }

    @Override
    public List<Reservation> presentLaneReservations(UUID UUIDLane) {
        return convertToListReservation(reservationRepository.presentLaneReservations(UUIDLane));
    }
}
