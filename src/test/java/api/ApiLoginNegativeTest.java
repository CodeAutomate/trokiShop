package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiLoginNegativeTest {

    @Test
    public void testLoginWithInvalidCredentials() {
        RestAssured.baseURI = "https://meinesuppe.de/api";

        given()
                .contentType(ContentType.JSON)
                .body("{\"username\":\"invalidUser\",\"password\":\"invalidPass\"}")
                .when()
                .post("/login")
                .then()
                .statusCode(404)
                .body("error", containsString("Invalid credentials"));
    }
}