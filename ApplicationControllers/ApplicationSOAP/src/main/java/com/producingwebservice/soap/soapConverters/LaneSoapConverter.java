package com.producingwebservice.soap.soapConverters;


import com.producingwebservice.soap.soapmodel.lanemodel.LaneSoap;
import model.Lane;

public class LaneSoapConverter {

    public static LaneSoap convertFromLane(Lane lane) {
        LaneSoap laneSoap = new LaneSoap();
        laneSoap.setUuid(String.valueOf(lane.getUuid()));
        lane.setType(lane.getType());
        return laneSoap;
    }

}
