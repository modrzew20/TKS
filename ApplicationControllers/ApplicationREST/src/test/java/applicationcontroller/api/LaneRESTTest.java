//package applicationcontroller.api;
//
//import model.Lane;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LaneRESTTest implements RESTTest {
//
//
//    @LocalServerPort
//    int port = 0;
//
//    @Autowired
//    TestRestTemplate testRestTemplate;
//
//    private String BASE_PATH;
//
//    ParameterizedTypeReference<List<Lane>> typeReference = new ParameterizedTypeReference<List<Lane>>() {
//    };
//
//    public LaneRESTTest() {
//    }
//
//    @PostConstruct
//    private void setBasePath() {
//        BASE_PATH = "http://localhost:" + port + "/";
//    }
//
//    @Test
//    void contextLoads() {
//        assertNotNull(BASE_PATH);
//        assertNotEquals(0, port);
//        assertNotNull(testRestTemplate);
//    }
//
//    @Test
//    void listAll() {
//        ResponseEntity<List<Lane>> response = testRestTemplate.exchange(BASE_PATH, HttpMethod.GET, null, typeReference);
//        assertTrue(response.getStatusCode().is2xxSuccessful());
//        List<Lane> laneList = response.getBody();
//        assertNotNull(laneList);
//        assertNotEquals(0, laneList.size());
//
//    }
//
//
//}
