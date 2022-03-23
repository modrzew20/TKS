package adapters;

import Port.In.LanePortIn;
import model.Lane;
import repository.LaneRepository;

import java.util.UUID;

public class LanePortInAdapter implements LanePortIn {

    LaneRepository laneRepository = new LaneRepository();
    LaneAdapter laneAdapter = new LaneAdapter();

    @Override
    public Lane create(Lane lane) {
        return laneAdapter.convertToLane(laneRepository.create(laneAdapter.convertFromLane(lane)));
    }

    @Override
    public Lane delete(UUID uuid) {
        return laneAdapter.convertToLane(laneRepository.delete(uuid));
    }

    @Override
    public Lane update(Lane lane) {
        return laneAdapter.convertToLane(laneRepository.update(laneAdapter.convertFromLane(lane)));
    }
}
