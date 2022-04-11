package service;


import Port.In.CreateReservationPort;
import Port.In.DeleteReservationPort;
import Port.In.UpdateReservationPort;
import Port.Out.*;
import model.Lane;
import model.Reservation;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final Object lock = new Object();
    private final ReadReservationPort readReservationPort;
    private final ReadLanePort readLanePort;
    private final ReadUserPort readUserPort;
    private final CreateReservationPort createReservationPort;
    private final UpdateReservationPort updateReservationPort;
    private final DeleteReservationPort deleteReservationPort;
    private final ClientsReservationPort clientsReservationPort;
    private final LanesReservationPort lanesReservationPort;

    @Autowired
    public ReservationService(ReadReservationPort readReservationPort, ReadLanePort readLanePort, ReadUserPort readUserPort, CreateReservationPort createReservationPort, UpdateReservationPort updateReservationPort, DeleteReservationPort deleteReservationPort, ClientsReservationPort clientsReservationPort, LanesReservationPort lanesReservationPort) {
        this.readReservationPort = readReservationPort;
        this.readLanePort = readLanePort;
        this.readUserPort = readUserPort;
        this.createReservationPort = createReservationPort;
        this.updateReservationPort = updateReservationPort;
        this.deleteReservationPort = deleteReservationPort;
        this.clientsReservationPort = clientsReservationPort;
        this.lanesReservationPort = lanesReservationPort;
    }

    public List<Reservation> readAllReservation() {
        synchronized (lock) {
            return readReservationPort.readAll();
        }
    }

    public Reservation addReservation(UUID clientsUUID, UUID UUIDLane, LocalDateTime start, LocalDateTime end) {

        Lane lane = readLanePort.readById(UUIDLane);
        User client = readUserPort.readById(clientsUUID);
        if (!readUserPort.readById(clientsUUID).getActive() || createReservationPort.reservedLine(UUIDLane, start, end) || lane == null || client == null)
            return null;
        synchronized (lock) {
            Reservation reservation = new Reservation(UUID.randomUUID(), lane, client, start, end);
            createReservationPort.create(reservation);
            return reservation;
        }
    }

    public Reservation readOneReservation(UUID uuid) {
        synchronized (lock) {
            return readReservationPort.readById(uuid);
        }
    }

    public List<Reservation> pastClientReservations(UUID clientsUUID) {
        synchronized (lock) {
            return clientsReservationPort.pastClientReservations(clientsUUID);
        }
    }

    public List<Reservation> presentClientReservations(UUID clientsUUID) {
        synchronized (lock) {
            return clientsReservationPort.presentClientReservations(clientsUUID);
        }
    }

    public List<Reservation> pastLaneReservations(UUID UUIDLane) {
        synchronized (lock) {
            return lanesReservationPort.pastLaneReservations(UUIDLane);
        }
    }

    public List<Reservation> presentLaneReservations(UUID UUIDLane) {
        synchronized (lock) {
            return lanesReservationPort.presentLaneReservations(UUIDLane);
        }
    }

    public Reservation endReservation(UUID uuid, LocalDateTime localDateTime) {
        synchronized (lock) {
            return deleteReservationPort.endReservation(uuid, localDateTime);
        }
    }

    public Reservation delete(UUID uuid) {
        synchronized (lock) {
            return deleteReservationPort.delete(uuid);
        }
    }

}
