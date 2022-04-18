package converters;

import model.Lane;
import modelView.LaneView;

import static converters.LaneTypeViewConverter.convertFromType;
import static converters.LaneTypeViewConverter.convertToType;

public class LaneViewConverter {


    public static Lane convertToLane(LaneView laneView) {

        return new Lane(laneView.getUuid(), convertToType(laneView.getType()));
    }

    public static LaneView convertFromLane(Lane lane) {
        return new LaneView(lane.getUuid(), convertFromType(lane.getType()));
    }
}
