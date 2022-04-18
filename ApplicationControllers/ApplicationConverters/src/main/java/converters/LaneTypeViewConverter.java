package converters;

import model.LANE_TYPE;
import modelView.LANE_TYPE_View;

public class LaneTypeViewConverter {

    public static LANE_TYPE_View convertFromType(LANE_TYPE lane_type) {
        switch (lane_type) {
            case normal -> {
                return LANE_TYPE_View.normal;
            }
            case vip -> {
                return LANE_TYPE_View.vip;
            }
            case premium -> {
                return LANE_TYPE_View.premium;
            }
            default -> {
                return null;
            }
        }
    }

    public static LANE_TYPE convertToType(LANE_TYPE_View lane_type_view) {
        switch (lane_type_view) {
            case normal -> {
                return LANE_TYPE.normal;
            }
            case vip -> {
                return LANE_TYPE.vip;
            }
            case premium -> {
                return LANE_TYPE.premium;
            }
            default -> {
                return null;
            }
        }
    }
}
