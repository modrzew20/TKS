package applicationcontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.LaneService;


@RestController
@RequestMapping(value = "/test")
public class TestController {

//    LaneService laneService;
//
//    @Autowired
//    public TestController(LaneService laneService) {
//        this.laneService = laneService;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity getTest() {
//        return ResponseEntity.status(200).body( laneService.readAllLane());
//    }


}
