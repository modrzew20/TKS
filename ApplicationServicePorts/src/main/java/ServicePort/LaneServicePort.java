package ServicePort;

import modelView.LaneView;

import java.util.List;
import java.util.UUID;

//TODO split into smaller interfaces
public interface LaneServicePort {

    List<LaneView> readAllLane();

    LaneView addLane(String lane_type);

    LaneView updateLane(UUID uuid, String lane_type);

    LaneView readOneLane(UUID uuid);

    LaneView deleteLine(UUID uuid);
}
