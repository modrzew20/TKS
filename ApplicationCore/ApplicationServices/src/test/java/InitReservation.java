import io.restassured.RestAssured;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class InitReservation {


    @Test
    public void addonneReservationTest() {

        // create user
        String clientsUUID = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "ResourceAdministrator")
                .formParam("login", "test1")
                .formParam("password", "testPvcord3")
                .when().post("http://localhost:8081/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        String clientsUUID2 = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "ResourceAdministrator")
                .formParam("login", "test2")
                .formParam("password", "testPvcord3")
                .when().post("http://localhost:8081/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");


        String laneUUID = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("type", "vip")
                .when().post("http://localhost:8081/Kregielnia-1.0-SNAPSHOT/api/lane/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        String laneUUID2 = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("type", "vip")
                .when().post("http://localhost:8081/Kregielnia-1.0-SNAPSHOT/api/lane/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        // dodanie rezerwacji poprawne
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("clientsUUID", clientsUUID)
                .formParam("laneUUID", laneUUID)
                .formParam("start", "2021-12-29T12:10:31")
                .formParam("end", "2022-12-29T12:30:31")
                .when().post("http://localhost:8081/Kregielnia-1.0-SNAPSHOT/api/reservation/add")
                .then()
                .assertThat()
                .statusCode(200);

        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("clientsUUID", clientsUUID)
                .formParam("laneUUID", laneUUID)
                .formParam("start", "2020-12-29T12:10:31")
                .formParam("end", "2020-12-29T12:30:31")
                .when().post("http://localhost:8081/Kregielnia-1.0-SNAPSHOT/api/reservation/add")
                .then()
                .assertThat()
                .statusCode(200);

        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("clientsUUID", clientsUUID2)
                .formParam("laneUUID", laneUUID2)
                .formParam("start", "2021-12-29T12:10:31")
                .formParam("end", "2022-12-29T12:30:31")
                .when().post("http://localhost:8081/Kregielnia-1.0-SNAPSHOT/api/reservation/add")
                .then()
                .assertThat()
                .statusCode(200);

        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("clientsUUID", clientsUUID2)
                .formParam("laneUUID", laneUUID2)
                .formParam("start", "2020-12-29T12:10:31")
                .formParam("end", "2020-12-29T12:30:31")
                .when().post("http://localhost:8081/Kregielnia-1.0-SNAPSHOT/api/reservation/add")
                .then()
                .assertThat()
                .statusCode(200);


    }


}
