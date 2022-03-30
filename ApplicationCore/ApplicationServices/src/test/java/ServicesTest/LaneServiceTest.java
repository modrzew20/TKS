package ServicesTest;

import model.Lane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import service.LaneService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LaneServiceTest {

    LaneService laneService;

    @BeforeEach
    private void init() {
        laneService = new LaneService();
        laneService.addLane("vip");
    }

    @Test
    private void addLaneTest() {
        int size = laneService.readAllLane().size();
        laneService.addLane("vip");
        assertEquals(size+1, laneService.readAllLane().size());
    }

    @Test
    private void updateLaneTest() {
        Lane lane = laneService.readAllLane().get(0);
        laneService.updateLane(lane.getUuid(),"premium");
        assertEquals(lane.getType(),laneService.readOneLane(lane.getUuid()).getType());
    }

    @Test
    private void deleteLaneTest() {
        Lane lane = laneService.readAllLane().get(0);
        int size = laneService.readAllLane().size();
        laneService.deleteLine(lane.getUuid());
        assertEquals(size-1, laneService.readAllLane().size());
    }





}
