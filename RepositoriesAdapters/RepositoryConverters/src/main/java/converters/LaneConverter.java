package converters;

import model.Lane;
import modelEnt.LaneEnt;

import java.util.List;

import static converters.LaneTypeConverter.convertFromType;
import static converters.LaneTypeConverter.convertToType;

public class LaneConverter {


    public static Lane convertToLane(LaneEnt laneEnt) {
        return new Lane(laneEnt.getUuid(), convertToType(laneEnt.getType()));
    }

    public static List<Lane> convertToLaneList(List<LaneEnt> laneEntList) {
        return laneEntList.stream().map(LaneConverter::convertToLane).toList();
    }

    public static LaneEnt convertFromLane(Lane lane) {
        return new LaneEnt(lane.getUuid(), convertFromType(lane.getType()));
    }

    public static List<LaneEnt> convertFromLaneList(List<Lane> laneList) {
        return laneList.stream().map(LaneConverter::convertFromLane).toList();
    }
}
