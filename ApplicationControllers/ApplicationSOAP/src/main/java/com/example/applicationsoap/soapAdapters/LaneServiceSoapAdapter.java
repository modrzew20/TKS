package com.example.applicationsoap.soapAdapters;//package com.example.applicationsoapapi.soapAdapters;

import ServicePort.LaneServicePort;
import com.example.applicationsoap.soapmodel.lanemodel.LaneSoap;
import model.LANE_TYPE;
import model.Lane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LaneService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.applicationsoap.soapConverters.LaneSoapConverter.convertFromLane;
import static com.example.applicationsoap.soapConverters.LaneSoapConverter.convertFromLaneList;


@Service
public class LaneServiceSoapAdapter implements LaneServicePort<LaneSoap> {

    //TODO failure with autowiring laneservice becouse it doesnt see package
    // when added package in main , problem with starting project
   // @Autowired
   // LaneService laneService;

    @Override
    public List<LaneSoap> readAllLane() {
        //return convertFromLaneList(laneService.readAllLane());
        List<Lane> list = new ArrayList<>();
        return convertFromLaneList(list);
    }

    @Override
    public LaneSoap addLane(String lane_type) {
        //return convertFromLane(laneService.addLane(lane_type));
        return new LaneSoap();
    }

    @Override
    public LaneSoap updateLane(UUID uuid, String lane_type) {
       // return convertFromLane(laneService.updateLane(uuid,lane_type));
        return new LaneSoap();
    }

    @Override
    public LaneSoap readOneLane(UUID uuid) {
        return convertFromLane(new Lane(uuid, LANE_TYPE.normal));
        //return convertFromLane(laneService.readOneLane(uuid));
    }

    @Override
    public LaneSoap deleteLine(UUID uuid) {
        return new LaneSoap();
    }
}
