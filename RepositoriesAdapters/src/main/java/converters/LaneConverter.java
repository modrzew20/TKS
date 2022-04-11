package converters;

import model.Lane;
import modelEnt.LaneEnt;

import java.util.ArrayList;
import java.util.List;

import static converters.LaneTypeConverter.convertFromType;
import static converters.LaneTypeConverter.convertToType;

public class LaneConverter {


    public static Lane convertToLane(LaneEnt laneEnt) {

        return new Lane(laneEnt.getUuid(), convertToType(laneEnt.getType()));
    }

    public static List<Lane> convertToLaneList(List<LaneEnt> laneEntList) {
        List<Lane> convertedList = new ArrayList<>();
        for (LaneEnt laneEnt : laneEntList) {
            convertedList.add(convertToLane(laneEnt));
        }
        return convertedList;
    }

    public static LaneEnt convertFromLane(Lane lane) {
        return new LaneEnt(lane.getUuid(), convertFromType(lane.getType()));
    }

    public static List<LaneEnt> convertFromLaneList(List<Lane> laneList) {
        List<LaneEnt> convertedList = new ArrayList<>();
        for (Lane lane : laneList) {
            convertedList.add(convertFromLane(lane));
        }
        return convertedList;
    }
}
