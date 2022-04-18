package converters;

import model.Lane;
import modelView.LaneView;

import java.util.ArrayList;
import java.util.List;

import static converters.LaneTypeViewConverter.convertFromType;
import static converters.LaneTypeViewConverter.convertToType;

public class LaneViewConverter {


    public static Lane convertToLane(LaneView laneView) {

        return new Lane(laneView.getUuid(), convertToType(laneView.getType()));
    }

    public static List<Lane> convertToLaneList(List<LaneView> laneViewList) {
        List<Lane> convertedList = new ArrayList<>();
        for (LaneView laneView : laneViewList) {
            convertedList.add(convertToLane(laneView));
        }
        return convertedList;
    }

    public static LaneView convertFromLane(Lane lane) {
        return new LaneView(lane.getUuid(), convertFromType(lane.getType()));
    }

    public static List<LaneView> convertFromLaneList(List<Lane> laneList) {
        List<LaneView> convertedList = new ArrayList<>();
        for (Lane lane : laneList) {
            convertedList.add(convertFromLane(lane));
        }
        return convertedList;
    }
}
