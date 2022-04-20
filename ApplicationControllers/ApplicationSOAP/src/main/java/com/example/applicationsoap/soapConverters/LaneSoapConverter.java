package com.example.applicationsoap.soapConverters;//package com.example.applicationsoapapi.soapConverters;

import com.example.applicationsoap.soapmodel.lanemodel.LaneSoap;
import model.Lane;

import java.util.ArrayList;
import java.util.List;

public class LaneSoapConverter {

    public static LaneSoap convertFromLane(Lane lane) {
        if(lane == null) return null;
        LaneSoap laneSoap = new LaneSoap();
        laneSoap.setUuid(String.valueOf(lane.getUuid()));
        lane.setType(lane.getType());
        return laneSoap;
    }

}
