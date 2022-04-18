package applicationcontroller.api;

import exceptions.LoginInUseException;
import model.LANE_TYPE;
import model.Lane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.LaneService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/lane")
public class LaneController {

    private final LaneService laneService;

    @Autowired
    public LaneController(LaneService laneService) {
        this.laneService = laneService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lane> readAllLane() {
        return laneService.readAllLane();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/filter/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lane> filterLines(@PathVariable() @NotBlank @Pattern(regexp = "vip|normal|premium") String type) {
        return laneService.readAllLane().stream().filter(lane -> lane.getType().equals(LANE_TYPE.valueOf(type))).collect(Collectors.toList());
    }


    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lane readLane(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return laneService.readOneLane(UUID.fromString(uuid));
    }

    //@Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
    //@Pattern(regexp = "vip|normal|premium")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lane addLine(@RequestParam("type") @NotBlank @Pattern(regexp = "vip|normal|premium") String type) throws LoginInUseException {
        return laneService.addLane(type);
    }


    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lane updateLine(@RequestParam("id") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid, @RequestParam @NotBlank @Pattern(regexp = "vip|normal|premium") String type) {
        return laneService.updateLane(UUID.fromString(uuid), type);
    }

    @DeleteMapping(value = "/delete/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lane deleteLane(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return laneService.deleteLine(UUID.fromString(uuid));
    }

}
