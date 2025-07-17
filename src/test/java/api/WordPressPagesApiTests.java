package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("WordPress API")
@Feature("Seiten-Endpunkte")
public class WordPressPagesApiTests {

    @Test
    @Story("Seiten sind erreichbar")
    @Description("Prüft, ob die Seiten-API erreichbar ist und mindestens eine Seite mit Titel liefert.")
    public void seitenSindErreichbarUndNichtLeer() {
        RestAssured.baseURI = "https://meinesuppe.de";

        given()
                .when()
                .get("/wp-json/wp/v2/pages")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].title.rendered", not(emptyString()));
    }

    @Test
    @Story("Impressum oder Kontakt vorhanden")
    @Description("Prüft, ob eine Seite mit 'Impressum' oder 'Kontakt' im Titel existiert.")
    public void impressumOderKontaktExistiert() {
        RestAssured.baseURI = "https://meinesuppe.de";

        given()
                .when()
                .get("/wp-json/wp/v2/pages")
                .then()
                .statusCode(200)
                .body("title.rendered", hasItem(anyOf(containsStringIgnoringCase("impressum"), containsStringIgnoringCase("kontakt"))));
    }
}