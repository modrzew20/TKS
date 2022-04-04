package service;


import Port.In.CreateLanePort;
import Port.In.DeleteLanePort;
import Port.In.UpdateLanePort;
import Port.Out.LanesReservationPort;
import Port.Out.ReadLanePort;
import model.LANE_TYPE;
import model.Lane;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LaneService {

    private final Object lock = new Object();
    private ReadLanePort readLanePort;
    private CreateLanePort createLanePort;
    private DeleteLanePort deleteLanePort;
    private UpdateLanePort updateLanePort;
    private LanesReservationPort reservationPortOut;

    public List<Lane> readAllLane() {
        synchronized (lock) {
            return readLanePort.readAll();
        }
    }

    public Lane addLane(String lane_type) {
        synchronized (lock) {
            return createLanePort.create(new Lane(null, LANE_TYPE.valueOf(lane_type)));
        }
    }

    public Lane updateLane(UUID uuid, String lane_type) {
        if (reservationPortOut.presentLaneReservations(uuid).size() != 0) return null;
        synchronized (lock) {
            return updateLanePort.update(new Lane(uuid, LANE_TYPE.valueOf(lane_type)));
        }
    }

    public Lane readOneLane(UUID uuid) {
        synchronized (lock) {
            return readLanePort.readById(uuid);
        }
    }

    public Lane deleteLine(UUID uuid) {
        if (reservationPortOut.presentLaneReservations(uuid).size() != 0) return null;
        synchronized (lock) {
            return deleteLanePort.delete(uuid);
        }
    }
}


