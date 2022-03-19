package adapters;

import com.example.kregielniaspring.model.LANE_TYPE;
import modelEnt.LANE_TYPE_Ent;

public class LaneTypeAdapter {

    public LANE_TYPE_Ent convertFromType(LANE_TYPE lane_type){
        if(lane_type == LANE_TYPE.normal) return LANE_TYPE_Ent.normal;
        if(lane_type == LANE_TYPE.vip) return LANE_TYPE_Ent.vip;
        if(lane_type == LANE_TYPE.premium) return LANE_TYPE_Ent.premium;
        return null;
    }
    public LANE_TYPE convertToType(LANE_TYPE_Ent lane_type_ent){
        if(lane_type_ent == LANE_TYPE_Ent.normal) return LANE_TYPE.normal;
        if(lane_type_ent == LANE_TYPE_Ent.vip) return LANE_TYPE.vip;
        if(lane_type_ent == LANE_TYPE_Ent.premium) return LANE_TYPE.premium;
        return null;
    }
}
