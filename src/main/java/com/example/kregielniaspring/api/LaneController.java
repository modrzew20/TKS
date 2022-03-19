package com.example.kregielniaspring.api;

import com.example.kregielniaspring.managers.LaneManager;
import com.example.kregielniaspring.model.LANE_TYPE;
import com.example.kregielniaspring.model.Lane;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/lane")
@Validated
public class LaneController {

    private final LaneManager laneManager;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readAllLane() {
        List<Lane> lanes = laneManager.readAllLane();
        if (lanes == null) return ResponseEntity.status(404).build();
        return ResponseEntity.ok(lanes);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/filter/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity filterLines(@PathVariable() @NotBlank @Pattern(regexp = "vip|normal|premium") String type) {
        List<Lane> lanes = laneManager.readAllLane().stream().filter(lane -> lane.getType().equals(LANE_TYPE.valueOf(type))).collect(Collectors.toList());
        if (lanes == null) return ResponseEntity.status(404).build();
        return ResponseEntity.ok(lanes);
    }


    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readLane(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid){
        Lane lane =laneManager.readOneLane(UUID.fromString(uuid));
        if (lane==null) return ResponseEntity.status(404).build();
        return ResponseEntity.ok(lane);
    }

    //@Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
    //@Pattern(regexp = "vip|normal|premium")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addLine(@RequestParam("type") @NotBlank @Pattern(regexp = "vip|normal|premium") String type) {
        Lane lane = laneManager.addLane(type);
        if (lane == null) return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(lane);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateLine(@RequestParam("id") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid , @RequestParam @NotBlank @Pattern(regexp = "vip|normal|premium") String type) {
        Lane lane = laneManager.updateLane(UUID.fromString(uuid), type);
        if (lane == null) return ResponseEntity.status(404).build();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteLane(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid ) {
        Lane lane = laneManager.deleteLine(UUID.fromString(uuid));
        if (lane == null) return ResponseEntity.status(404).build();
        return ResponseEntity.ok().build();
    }

}
