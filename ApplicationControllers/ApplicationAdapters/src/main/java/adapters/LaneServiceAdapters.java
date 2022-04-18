package adapters;

import ServicePort.LaneServicePort;
import converters.LaneViewConverter;
import modelView.LaneView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LaneService;

import java.util.List;
import java.util.UUID;

@Service
public class LaneServiceAdapters implements LaneServicePort {

    @Autowired
    LaneService laneService;

    @Override
    public List<LaneView> readAllLane() {
        return laneService.readAllLane().stream().map(LaneViewConverter::convertFromLane).toList();
    }

    @Override
    public LaneView addLane(String lane_type) {
        return LaneViewConverter.convertFromLane(laneService.addLane(lane_type));
    }

    @Override
    public LaneView updateLane(UUID uuid, String lane_type) {
        return LaneViewConverter.convertFromLane(laneService.updateLane(uuid, lane_type));
    }

    @Override
    public LaneView readOneLane(UUID uuid) {
        return LaneViewConverter.convertFromLane(laneService.readOneLane(uuid));
    }

    @Override
    public LaneView deleteLine(UUID uuid) {
        return LaneViewConverter.convertFromLane(laneService.deleteLine(uuid));
    }
}
