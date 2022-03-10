import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JsonplaceherGETTwoTest {

    //Given - konfiguracja
    //When - wysłanie requestu
    // Then - asercje

    @Test
    public void jsonplaceholderReadAllUsers(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(200)
                .extract()
                .response();
        JsonPath json = response.jsonPath();
        List<Object> names = json.getList("name");
        Assertions.assertEquals(10, names.size());
    }

    @Test
    public void jsonplaceholderReadOneUser() {
            given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                    .then()
                    .statusCode(200)
                    .body("name", equalTo("Leanne Graham"))
                    .body("username", equalTo("Bret"))
                    .body("address.street", equalTo("Kulas Light"));
    }

    @Test
    public void jsonplaceholderReadOneUserWithPathVariable() {
        Response response = given().pathParam("userId", 1)
                .when()
                .get("https://jsonplaceholder.typicode.com/users/{userId}");

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Bret", json.get("username"));
        Assertions.assertEquals("Kulas Light", json.get("address.street"));
        //System.out.print(response.asString());
    }

    @Test
    public void jsonplaceholderReadOneUserWithQueryParams() {
        Response response = given().queryParam("username", "Bret")
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals("Leanne Graham", json.getList("name").get(0));
        Assertions.assertEquals("Bret", json.getList("username").get(0));
        Assertions.assertEquals("Kulas Light", json.getList("address.street").get(0));
        //System.out.print(response.asString());
    }
}
