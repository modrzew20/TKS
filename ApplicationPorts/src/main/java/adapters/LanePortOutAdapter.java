package adapters;

import Port.Out.ReadLanePort;
import model.Lane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.LaneRepository;

import java.util.List;
import java.util.UUID;

import static converters.LaneConverter.convertToLane;
import static converters.LaneConverter.convertToLaneList;

@Component()
public class LanePortOutAdapter implements ReadLanePort {

    @Autowired
    LaneRepository laneRepository;

    @Override
    public List<Lane> readAll() {
        return convertToLaneList(laneRepository.readAll());
    }

    @Override
    public Lane readById(UUID uuid) {
        return convertToLane(laneRepository.readById(uuid));
    }
}
