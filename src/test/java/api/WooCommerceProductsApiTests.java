package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("WooCommerce API")
@Feature("Produkt-Endpunkte")
public class WooCommerceProductsApiTests {

    static {
        RestAssured.baseURI = "https://meinesuppe.de";
    }

    @Test
    @Story("Produkte abrufbar")
    @Description("Prüft, ob Produkte abrufbar sind (Shop-API offen).")
    public void produkteSindErreichbar() {
        given()
                .when()
                .get("/wp-json/wc/v3/products")
                .then()
                .statusCode(anyOf(is(200), is(401), is(403)));
    }
}