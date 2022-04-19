package producingwebservice.soapConverters;

import io.spring.guides.gs_producing_web_service.LANETYPESoap;
import model.LANE_TYPE;

public class LaneTypeSoapConverter {

    public static LANETYPESoap convertFromType(LANE_TYPE lane_type) {
        switch (lane_type) {
            case normal -> {
                return LANETYPESoap.NORMAL;
            }
            case vip -> {
                return LANETYPESoap.VIP;
            }
            case premium -> {
                return LANETYPESoap.PREMIUM;
            }
            default -> {
                return null;
            }
        }
    }
}
