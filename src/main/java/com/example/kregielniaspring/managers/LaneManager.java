package com.example.kregielniaspring.managers;

import com.example.kregielniaspring.model.LANE_TYPE;
import com.example.kregielniaspring.model.Lane;
import com.example.kregielniaspring.repository.LaneRepository;
import com.example.kregielniaspring.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LaneManager {

    private final LaneRepository laneRepository;
    private final ReservationRepository reservationRepository;

    private final Object lock = new Object();

    public List<Lane> readAllLane() {
        synchronized(lock) {
            return laneRepository.readAll();
        }
    }

    public Lane addLane(String lane_type){
        synchronized(lock) {
            return laneRepository.create(new Lane(null, LANE_TYPE.valueOf(lane_type)));
        }
    }

    public Lane updateLane(UUID uuid, String lane_type) {
        if (reservationRepository.presentLaneReservations(uuid).size() != 0) return null;
        synchronized(lock) {
            return laneRepository.update(new Lane(uuid, LANE_TYPE.valueOf(lane_type)));
        }
    }

    public Lane readOneLane(UUID uuid) {
        synchronized(lock) {
            return laneRepository.readById(uuid);
        }
    }

    public Lane deleteLine(UUID uuid) {
        if (reservationRepository.presentLaneReservations(uuid).size() != 0) return null;
        synchronized(lock) {
            return laneRepository.delete(uuid);
        }
    }
}


