package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("WordPress API")
@Feature("Post-Endpunkte")
public class WordPressPostsApiTests {

    static {
        RestAssured.baseURI = "https://meinesuppe.de";
    }

    @Test
    @Story("Posts abrufbar")
    @Description("Prüft, ob Blog-Posts vorhanden sind.")
    public void postsSindErreichbarUndNichtLeer() {
        given()
                .when()
                .get("/wp-json/wp/v2/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    @Story("Suche funktioniert")
    @Description("Prüft, ob die Suchfunktion Ergebnisse liefert.")
    public void sucheLiefertErgebnisseOderLeereListe() {
        given()
                .queryParam("search", "test")
                .when()
                .get("/wp-json/wp/v2/posts")
                .then()
                .statusCode(200)
                .body("$", notNullValue());
    }
}