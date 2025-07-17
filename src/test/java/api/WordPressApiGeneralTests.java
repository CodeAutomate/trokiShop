package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("WordPress API")
@Feature("Allgemeine API-Checks")
public class WordPressApiGeneralTests {

    static {
        RestAssured.baseURI = "https://meinesuppe.de";
    }

    @Test
    @Story("Fehlerfall: ungültige Seite")
    @Description("Prüft, ob ein ungültiger Endpunkt korrekt mit 404 antwortet.")
    public void ungueltigerEndpunktGibt404() {
        given()
                .when()
                .get("/wp-json/wp/v2/doesnotexist")
                .then()
                .statusCode(404);
    }

    @Test
    @Story("CORS-Header vorhanden")
    @Description("Prüft, ob die API CORS-Header für GET-Anfragen setzt, wenn ein Origin-Header gesendet wird.")
    public void corsHeaderVorhanden() {
        given()
                .header("Origin", "https://meinesuppe.de")
                .when()
                .get("/wp-json/wp/v2/pages")
                .then()
                .header("Access-Control-Allow-Origin", notNullValue());
    }

    @Test
    @Story("Sicherheitsheader vorhanden")
    @Description("Prüft, ob sicherheitsrelevante Header gesetzt sind.")
    public void sicherheitsHeaderVorhanden() {
        given()
                .when()
                .get("/wp-json/wp/v2/pages")
                .then()
                .header("X-Content-Type-Options", notNullValue());
    }
}