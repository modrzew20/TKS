package adapters;


import Port.In.CreateLanePort;
import Port.In.DeleteLanePort;
import Port.In.UpdateLanePort;
import model.Lane;
import repository.LaneRepository;

import java.util.UUID;

import static converters.LaneConverter.convertFromLane;
import static converters.LaneConverter.convertToLane;

public class LanePortInAdapter implements CreateLanePort, DeleteLanePort, UpdateLanePort {

    LaneRepository laneRepository = new LaneRepository();

    @Override
    public Lane create(Lane lane) {
        return convertToLane(laneRepository.create(convertFromLane(lane)));
    }

    @Override
    public Lane delete(UUID uuid) {
        return convertToLane(laneRepository.delete(uuid));
    }

    @Override
    public Lane update(Lane lane) {
        return convertToLane(laneRepository.update(convertFromLane(lane)));
    }
}
