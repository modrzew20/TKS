package producingwebservice.soapConverters;

import io.spring.guides.gs_producing_web_service.LaneSoap;
import model.Lane;

public class LaneSoapConverter {

    public static LaneSoap convertFromLane(Lane lane) {
        LaneSoap laneSoap = new LaneSoap();
        laneSoap.setUuid(String.valueOf(lane.getUuid()));
        lane.setType(lane.getType());
        return laneSoap;
    }

}
