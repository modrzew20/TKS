package adapters;

import ServicePort.ReservationServicePort;
import converters.ReservationViewConverter;
import modelView.ReservationView;
import org.springframework.beans.factory.annotation.Autowired;
import service.ReservationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ReservationServiceAdapters implements ReservationServicePort {
    
    @Autowired
    ReservationService reservationService;
    
    @Override
    public List<ReservationView> readAllReservation() {
        return reservationService.readAllReservation().stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public ReservationView addReservation(UUID clientsUUID, UUID laneUUID, LocalDateTime start, LocalDateTime end) {
        return ReservationViewConverter.convertFromReservation(reservationService.addReservation(clientsUUID, laneUUID, start, end));
    }

    @Override
    public ReservationView readOneReservation(UUID uuid) {
        return ReservationViewConverter.convertFromReservation(reservationService.readOneReservation(uuid));
    }

    @Override
    public List<ReservationView> pastClientReservations(UUID clientsUUID) {
        return reservationService.pastClientReservations(clientsUUID).stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public List<ReservationView> presentClientReservations(UUID clientsUUID) {
        return reservationService.presentClientReservations(clientsUUID).stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public List<ReservationView> pastLaneReservations(UUID laneUUID) {
        return reservationService.pastLaneReservations(laneUUID).stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public List<ReservationView> presentLaneReservations(UUID laneUUID) {
        return reservationService.presentLaneReservations(laneUUID).stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public ReservationView endReservation(UUID uuid, LocalDateTime localDateTime) {
        return ReservationViewConverter.convertFromReservation(reservationService.endReservation(uuid, localDateTime));
    }

    @Override
    public ReservationView delete(UUID uuid) {
        return ReservationViewConverter.convertFromReservation(reservationService.delete(uuid));
    }
}
