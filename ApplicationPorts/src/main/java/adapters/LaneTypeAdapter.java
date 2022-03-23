package adapters;

import model.LANE_TYPE;
import modelEnt.LANE_TYPE_Ent;

public class LaneTypeAdapter {

    public LANE_TYPE_Ent convertFromType(LANE_TYPE lane_type) {
        switch (lane_type) {
            case normal -> {
                return LANE_TYPE_Ent.normal;
            }
            case vip -> {
                return LANE_TYPE_Ent.vip;
            }
            case premium -> {
                return LANE_TYPE_Ent.premium;
            }
            default -> {
                return null;
            }
        }
    }

    public LANE_TYPE convertToType(LANE_TYPE_Ent lane_type_ent) {
        switch (lane_type_ent) {
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
