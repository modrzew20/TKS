package adapters;

import Port.Out.ReadLanePort;
import model.Lane;
import repository.LaneRepository;
import java.util.List;
import java.util.UUID;
import static aggregates.LaneAdapter.convertToLane;
import static aggregates.LaneAdapter.convertToLaneList;

public class LanePortOutAdapter implements ReadLanePort {

    LaneRepository laneRepository = new LaneRepository();

    @Override
    public List<Lane> readAll() {
        return convertToLaneList(laneRepository.readAll());
    }

    @Override
    public Lane readById(UUID uuid) {
        return convertToLane(laneRepository.readById(uuid));
    }
}
