package com.example.kregielniaspring.service;


import Port.In.LanePortIn;
import Port.Out.LanePortOut;
import Port.Out.ReservationPortOut;
import com.example.kregielniaspring.model.LANE_TYPE;
import com.example.kregielniaspring.model.Lane;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LaneService {

    private LanePortOut lanePortOut;
    private LanePortIn lanePortIn;
    private ReservationPortOut reservationPortOut;

    private final Object lock = new Object();

    public List<Lane> readAllLane() {
        synchronized(lock) {
            return lanePortOut.readAll();
        }
    }

    public Lane addLane(String lane_type){
        synchronized(lock) {
            return lanePortIn.create(new Lane(null, LANE_TYPE.valueOf(lane_type)));
        }
    }

    public Lane updateLane(UUID uuid, String lane_type) {
        if (reservationPortOut.presentLaneReservations(uuid).size() != 0) return null;
        synchronized(lock) {
            return lanePortIn.update(new Lane(uuid, LANE_TYPE.valueOf(lane_type)));
        }
    }

    public Lane readOneLane(UUID uuid) {
        synchronized(lock) {
            return lanePortOut.readById(uuid);
        }
    }

    public Lane deleteLine(UUID uuid) {
        if (reservationPortOut.presentLaneReservations(uuid).size() != 0) return null;
        synchronized(lock) {
            return lanePortIn.delete(uuid);
        }
    }
}


