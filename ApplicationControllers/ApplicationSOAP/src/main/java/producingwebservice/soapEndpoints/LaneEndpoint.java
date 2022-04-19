package producingwebservice.soapEndpoints;

import io.spring.guides.gs_producing_web_service.ReadLaneRequest;
import io.spring.guides.gs_producing_web_service.ReadLaneResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import producingwebservice.soapAdapters.LaneServiceSoapAdapter;

import java.util.UUID;

@Endpoint
public class LaneEndpoint {
    private static final String URI = "http://localhost:8080/soap";

    private final LaneServiceSoapAdapter laneServiceSoapAdapter;

    @Autowired
    public LaneEndpoint(LaneServiceSoapAdapter laneServiceSoapAdapter) {
        this.laneServiceSoapAdapter = laneServiceSoapAdapter;
    }

    @PayloadRoot(namespace = URI, localPart = "readLaneRequest")
    @ResponsePayload
    public ReadLaneResponse readLaneResponse(@RequestPayload ReadLaneRequest request) {
        ReadLaneResponse response = new ReadLaneResponse();
        response.setLaneSoap(laneServiceSoapAdapter.readOneLane(UUID.fromString(request.getUuid())));
        return response;
    }
}