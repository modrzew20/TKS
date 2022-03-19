package adapters;

import Port.Out.LanePortOut;
import com.example.kregielniaspring.model.Lane;
import repository.LaneRepository;

import java.util.List;
import java.util.UUID;

public class LanePortOutAdapter implements LanePortOut {

    LaneAdapter laneAdapter = new LaneAdapter();
    LaneRepository laneRepository = new LaneRepository();

    @Override
    public List<Lane> readAll() {
        return laneAdapter.convertToLaneList(laneRepository.readAll());
    }

    @Override
    public Lane readById(UUID uuid) {
        return laneAdapter.convertToLane(laneRepository.readById(uuid));
    }
}
