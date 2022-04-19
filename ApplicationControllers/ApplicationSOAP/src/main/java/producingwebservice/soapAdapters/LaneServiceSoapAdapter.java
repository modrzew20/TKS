package producingwebservice.soapAdapters;

import ServicePort.LaneServicePort;
import io.spring.guides.gs_producing_web_service.LaneSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LaneService;

import java.util.List;
import java.util.UUID;

import static producingwebservice.soapConverters.LaneSoapConverter.convertFromLane;

@Service
public class LaneServiceSoapAdapter implements LaneServicePort<LaneSoap> {

    @Autowired
    LaneService laneService;

    @Override
    public List<LaneSoap> readAllLane() {
        return null;
    }

    @Override
    public LaneSoap addLane(String lane_type) {
        return null;
    }

    @Override
    public LaneSoap updateLane(UUID uuid, String lane_type) {
        return null;
    }

    @Override
    public LaneSoap readOneLane(UUID uuid) {
        return convertFromLane(laneService.readOneLane(uuid));
    }

    @Override
    public LaneSoap deleteLine(UUID uuid) {
        return null;
    }
}
