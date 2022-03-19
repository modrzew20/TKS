package adapters;


import com.example.kregielniaspring.model.Lane;
import modelEnt.LaneEnt;

import java.util.ArrayList;
import java.util.List;

public class LaneAdapter {

    public Lane convertToLane(LaneEnt laneEnt) {
        return new Lane(laneEnt.getUuid(),laneEnt.getType());
    }

    public List<Lane> convertToLaneList(List<LaneEnt> laneEntList) {
        List<Lane> convertedList = new ArrayList<>();
        for ( LaneEnt laneEnt : laneEntList) {
            convertedList.add(convertToLane(laneEnt));
        }
        return convertedList;
    }

    public LaneEnt convertFromLane(Lane lane) {
        return new LaneEnt(lane.getUuid(),lane.getType());
    }

    public List<LaneEnt> convertFromLaneList(List<Lane> laneList) {
        List<LaneEnt> convertedList = new ArrayList<>();
        for ( Lane lane : laneList) {
            convertedList.add(convertFromLane(lane));
        }
        return convertedList;
    }
}
