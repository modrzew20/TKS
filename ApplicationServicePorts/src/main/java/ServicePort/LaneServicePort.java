package ServicePort;

import java.util.List;
import java.util.UUID;

//TODO split into smaller interfaces
public interface LaneServicePort<T> {

    List<T> readAllLane();

    T addLane(String lane_type);

    T updateLane(UUID uuid, String lane_type);

    T readOneLane(UUID uuid);

    T deleteLine(UUID uuid);
}
