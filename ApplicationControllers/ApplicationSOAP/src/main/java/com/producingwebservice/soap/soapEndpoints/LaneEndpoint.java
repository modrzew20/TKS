package com.producingwebservice.soap.soapEndpoints;

import com.producingwebservice.soap.soapmodel.lanemodel.ReadLaneRequest;
import com.producingwebservice.soap.soapmodel.lanemodel.ReadLaneResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.producingwebservice.soap.soapAdapters.LaneServiceSoapAdapter;

import java.util.UUID;

@Endpoint
public class LaneEndpoint {
    private static final String URI = "http://producingwebservice.com/soap/soapModel/laneModel";

    private final LaneServiceSoapAdapter laneServiceSoapAdapter;

    @Autowired
    public LaneEndpoint(LaneServiceSoapAdapter laneServiceSoapAdapter) {
        this.laneServiceSoapAdapter = laneServiceSoapAdapter;
    }

    @PayloadRoot(namespace = URI, localPart = "ReadLaneRequest")
    @ResponsePayload
    public ReadLaneResponse readLaneResponse(@RequestPayload ReadLaneRequest request) {
        ReadLaneResponse response = new ReadLaneResponse();
        response.setLaneSoap(laneServiceSoapAdapter.readOneLane(UUID.fromString(request.getUuid())));
        return response;
    }
}