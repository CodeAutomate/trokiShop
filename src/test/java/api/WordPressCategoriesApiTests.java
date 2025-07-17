package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("WordPress API")
@Feature("Kategorien-Endpunkte")
public class WordPressCategoriesApiTests {

    static {
        RestAssured.baseURI = "https://meinesuppe.de";
    }

    @Test
    @Story("Kategorien abrufbar")
    @Description("Prüft, ob Beitragskategorien abrufbar sind.")
    public void kategorienSindErreichbar() {
        given()
                .when()
                .get("/wp-json/wp/v2/categories")
                .then()
                .statusCode(200);
    }
}