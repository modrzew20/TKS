import io.restassured.RestAssured;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@Disabled
public class UserControllerTest {
    @Test
    public void create() {
        // create user
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "Client")
                .formParam("login", "testUser1")
                .formParam("password", "testPassword1")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .body("active", equalTo(true))
                .body("login", equalTo("testUser1"))
                .body("password", equalTo("testPassword1"));
    }

    @Test
    public void read() {
        // create user
        String uuid = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "Administrator")
                .formParam("login", "testUser2")
                .formParam("password", "testPassword2")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        // read user
        RestAssured.when().get("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/{uuid}", uuid)
                .then()
                .assertThat()
                .statusCode(200)
                .body("active", equalTo(true))
                .body("login", equalTo("testUser2"))
                .body("password", equalTo("testPassword2"))
                .body("uuid", equalTo(uuid));
    }

    @Test
    public void update() {
        // create user
        String uuid = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "ResourceAdministrator")
                .formParam("login", "testUser3")
                .formParam("password", "testPassword3")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        // update user
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("id", uuid)
                .formParam("login", "updatedUser3")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/update")
                .then()
                .assertThat()
                .statusCode(200)
                .body("active", equalTo(true))
                .body("login", equalTo("updatedUser3"))
                .body("password", equalTo("testPassword3"))
                .body("uuid", equalTo(uuid));
    }

    @Test
    public void updateWithWrongLogin() {
        // create user
        String uuid = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "ResourceAdministrator")
                .formParam("login", "testUser3")
                .formParam("password", "testPassword3")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        // update user
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("id", uuid)
                .formParam("login", "")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/update")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void validate() {
        // invalid accessLevel
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "invalidAccessLevel")
                .formParam("login", "testUser4")
                .formParam("password", "testPassword4")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(400);

        // valid accessLevel
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "Administrator")
                .formParam("login", "testUser4")
                .formParam("password", "testPassword4")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(200);

        // invalid uuid
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("id", "invalidId")
                .formParam("login", "testUser4")
                .formParam("password", "testPassword4")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/update")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void uniqueLogin() {
        // create user
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "ResourceAdministrator")
                .formParam("login", "testUser5")
                .formParam("password", "testPassword5")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(200);
        // create user with same login
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "ResourceAdministrator")
                .formParam("login", "testUser5")
                .formParam("password", "testPassword5")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void emptyLogin() {
        // create user with empty login
        RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "ResourceAdministrator")
                .formParam("login", "")
                .formParam("password", "testPassword5")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void activate() {
        // create user
        String uuid = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("accessLevel", "ResourceAdministrator")
                .formParam("login", "testUser6")
                .formParam("password", "testPassword6")
                .when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/add")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("uuid");

        // deactivate user
        RestAssured.when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/deactivate/{uuid}", uuid)
                .then()
                .assertThat()
                .statusCode(200)
                .body("active", equalTo(false))
                .body("login", equalTo("testUser6"))
                .body("password", equalTo("testPassword6"))
                .body("uuid", equalTo(uuid));

        //assert active = false
        RestAssured.when().get("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/{uuid}", uuid)
                .then()
                .assertThat()
                .statusCode(200)
                .body("active", equalTo(false))
                .body("login", equalTo("testUser6"))
                .body("password", equalTo("testPassword6"))
                .body("uuid", equalTo(uuid));

        // activate user
        RestAssured.when().post("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/activate/{uuid}", uuid)
                .then()
                .assertThat()
                .statusCode(200)
                .body("active", equalTo(true))
                .body("login", equalTo("testUser6"))
                .body("password", equalTo("testPassword6"))
                .body("uuid", equalTo(uuid));

        //assert active = true
        RestAssured.when().get("http://localhost:8080/Kregielnia-1.0-SNAPSHOT/api/user/{uuid}", uuid)
                .then()
                .assertThat()
                .statusCode(200)
                .body("active", equalTo(true))
                .body("login", equalTo("testUser6"))
                .body("password", equalTo("testPassword6"))
                .body("uuid", equalTo(uuid));
    }
}
