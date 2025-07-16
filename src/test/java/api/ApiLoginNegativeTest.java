package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Description;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiLoginNegativeTest {

    @Test
    @Description("API-Login-Negativtest: ungültige Zugangsdaten liefern Fehler und Status 401")
    public void testLoginWithInvalidCredentials() {
        RestAssured.baseURI = "https://meinesuppe.de/api";

        given()
                .contentType(ContentType.JSON)
                .body("{\"username\":\"invalidUser\",\"password\":\"invalidPass\"}")
                .when()
                .post("/login")
                .then()
                .statusCode(401)
                .body("error", containsString("Invalid credentials"));
    }
}