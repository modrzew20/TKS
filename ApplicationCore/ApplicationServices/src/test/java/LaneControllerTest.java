import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.LANE_TYPE;
import model.Lane;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Disabled
class LaneControllerTest {

    private final String URL = "http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/";

    private ArrayList<Lane> repoLanes = new ArrayList<>();


    @BeforeEach
    public void cleanUp() throws Exception {
        ContentType contentType = ContentType.ANY;
        URI request_uri = URI.create(URL + "lane");

        Gson gson = new Gson();

        String json = RestAssured.given().contentType(contentType).get(request_uri).getBody().asString();

        Type listType = new TypeToken<List<Lane>>() {
        }.getType();
        List<Lane> lanes = gson.fromJson(json, listType);

        ArrayList<String> types = new ArrayList<>(3) {
        };
        types.add("normal");
        types.add("premium");
        types.add("vip");


        for (int i = lanes.size() - 1; i >= 0; i--) {
            RestAssured.delete(URL + "lane/delete/" + lanes.get(i).getUuid()).then().assertThat().statusCode(200);
        }

        repoLanes = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            io.restassured.response.Response response = RestAssured.given().formParam("type", types.get(i % 3)).post(URL + "lane/add");
            response.then().assertThat().statusCode(200);
            String addJson = response.getBody().asString();
            Lane lane = gson.fromJson(addJson, Lane.class);
            repoLanes.add(lane);
        }
    }

    @Test
    void readAllLane() {
        Gson gson = new Gson();

        URI request_uri = URI.create(URL + "lane");
        ContentType contentType = ContentType.ANY;

        int expected_status_code = 200;
        int expected_result_size = 6;

        io.restassured.response.Response response = RestAssured.given().contentType(contentType).get(request_uri);
        response.then().assertThat()
                .statusCode(expected_status_code).body("size()", is(expected_result_size));
    }

    @Test
    void readLine() {
        URI request_uri = URI.create(URL + "lane/");
        ContentType contentType = ContentType.ANY;

        String id_to_search = repoLanes.get(0).getUuid().toString();
        String not_existing_id = "9f77375c-7d30-4eca-a830-aebec2ddd8a1";
        String bad_id = "XD";

        int expected_result_size = 6;

        RestAssured.given().contentType(contentType).get(request_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(expected_result_size));

        RestAssured.given().contentType(contentType).get(request_uri + id_to_search).then().assertThat()
                .statusCode(Response.SC_OK).body("type", equalTo("normal"))
                .body("uuid", equalTo(id_to_search));

        RestAssured.given().contentType(contentType).get(request_uri + bad_id).then().assertThat()
                .statusCode(Response.SC_BAD_REQUEST).body("trace", containsString("ConstraintViolationException"));

        RestAssured.given().contentType(contentType).get(request_uri + not_existing_id).then().assertThat()
                .statusCode(Response.SC_NOT_FOUND);
    }

    @Test
    void addLine() {
        URI request_uri = URI.create(URL + "lane/add");
        URI read_all_uri = URI.create(URL + "lane");
        ContentType contentType = ContentType.URLENC;

        int initial_size = 6;
        int post_add_size = 7;

        // Przed dodaniem
        RestAssured.given().contentType(contentType).get(read_all_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(initial_size));

        RestAssured.given().contentType(contentType).formParam("type", "normal").post(request_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("type", containsString("normal"));

        // Po dodaniu
        RestAssured.given().contentType(contentType).get(read_all_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(post_add_size));

        RestAssured.given().contentType(contentType).formParam("type", "zly_typ").post(request_uri).then().assertThat()
                .statusCode(Response.SC_BAD_REQUEST).body("trace", containsString("ConstraintViolationException"));

        // Po nieudanej próbie
        RestAssured.given().contentType(contentType).get(read_all_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(post_add_size));
    }

    @Test
    void updateLine() {
        ContentType contentType = ContentType.URLENC;
        String lane_uuid = repoLanes.get(0).getUuid().toString();
        LANE_TYPE lane_type = repoLanes.get(0).getType();

        String wrong_uuid = "9f77375c-7d30-4eca-a830-aebec2ddd8a1";
        String wrong_type = "XD";
        String bad_uuid = "witam 🦍🦍🦍";

        URI request_uri = URI.create(URL + "lane/update");
        URI read_all_uri = URI.create(URL + "lane/");

        int expected_result_size = 6;

        // pre
        RestAssured.given().contentType(contentType).get(read_all_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(expected_result_size));

        RestAssured.given().contentType(contentType).get(read_all_uri + lane_uuid).then().assertThat()
                .statusCode(Response.SC_OK).body("type", equalTo(lane_type.toString()))
                .body("uuid", equalTo(lane_uuid));


        // git
        RestAssured.given().contentType(contentType)
                .formParam("id", lane_uuid)
                .formParam("type", "premium").post(request_uri).then().assertThat()
                .statusCode(Response.SC_OK);

        RestAssured.given().contentType(contentType).get(read_all_uri + lane_uuid).then().assertThat()
                .statusCode(Response.SC_OK).body("type", equalTo("premium"))
                .body("uuid", equalTo(lane_uuid));


        RestAssured.given().contentType(contentType)
                .formParam("id", lane_uuid)
                .formParam("type", "vip").post(request_uri).then().assertThat()
                .statusCode(Response.SC_OK);

        RestAssured.given().contentType(contentType).get(read_all_uri + lane_uuid).then().assertThat()
                .statusCode(Response.SC_OK).body("type", equalTo("vip"))
                .body("uuid", equalTo(lane_uuid));


        RestAssured.given().contentType(contentType)
                .formParam("id", lane_uuid)
                .formParam("type", "normal").post(request_uri).then().assertThat()
                .statusCode(Response.SC_OK);

        RestAssured.given().contentType(contentType).get(read_all_uri + lane_uuid).then().assertThat()
                .statusCode(Response.SC_OK).body("type", equalTo("normal"))
                .body("uuid", equalTo(lane_uuid));


        // nie git
        RestAssured.given().contentType(contentType)
                .formParam("id", wrong_uuid)
                .formParam("type", "normal").post(request_uri).then().assertThat()
                .statusCode(Response.SC_NOT_FOUND);

        RestAssured.given().contentType(contentType).get(read_all_uri + lane_uuid).then().assertThat()
                .statusCode(Response.SC_OK).body("type", equalTo("normal"))
                .body("uuid", equalTo(lane_uuid));


        RestAssured.given().contentType(contentType)
                .formParam("id", bad_uuid)
                .formParam("type", "normal").post(request_uri).then().assertThat()
                .statusCode(Response.SC_BAD_REQUEST).body("trace", containsString("ConstraintViolationException"));

        RestAssured.given().contentType(contentType).get(read_all_uri + lane_uuid).then().assertThat()
                .statusCode(Response.SC_OK).body("type", equalTo("normal"))
                .body("uuid", equalTo(lane_uuid));


        RestAssured.given().contentType(contentType)
                .formParam("id", wrong_uuid)
                .formParam("type", wrong_type).post(request_uri).then().assertThat()
                .statusCode(Response.SC_BAD_REQUEST).body("trace", containsString("ConstraintViolationException"));


        RestAssured.given().contentType(contentType).get(read_all_uri + lane_uuid).then().assertThat()
                .statusCode(Response.SC_OK).body("type", equalTo("normal"))
                .body("uuid", equalTo(lane_uuid));

        // post
        RestAssured.given().contentType(contentType).get(read_all_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(expected_result_size));
    }

    @Test
    void delete() {
        ContentType contentType = ContentType.URLENC;
        String lane_uuid = repoLanes.get(0).getUuid().toString();
        String wrong_uuid = "XD";

        URI request_uri = URI.create(URL + "lane/delete/");
        URI read_all_uri = URI.create(URL + "lane/");

        int expected_result_size = 6;
        int expected_result_size_after_delete = 5;

        // pre
        RestAssured.given().contentType(contentType).get(read_all_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(expected_result_size));

        // git
        RestAssured.given().contentType(contentType).delete(request_uri + lane_uuid).then().assertThat()
                .statusCode(Response.SC_OK);

        RestAssured.given().contentType(contentType).get(read_all_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(expected_result_size_after_delete));

        // nie git

        RestAssured.given().contentType(contentType).delete(request_uri + lane_uuid).then().assertThat()
                .statusCode(Response.SC_NOT_FOUND);

        RestAssured.given().contentType(contentType).get(read_all_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(expected_result_size_after_delete));

        RestAssured.given().contentType(contentType).delete(request_uri + wrong_uuid).then().assertThat()
                .statusCode(Response.SC_BAD_REQUEST).body("trace", containsString("ConstraintViolationException"));

        RestAssured.given().contentType(contentType).get(read_all_uri).then().assertThat()
                .statusCode(Response.SC_OK).body("size()", is(expected_result_size_after_delete));

    }
}