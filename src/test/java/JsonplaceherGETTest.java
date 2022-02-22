import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonplaceherGETTest {

    //Given - konfiguracja
    //When - wysłanie requestu
    // Then - asercje

    @Test
    public void jsonplaceholderReadAllUsers(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");
        System.out.print(response);

        Assertions.assertEquals(200, response.statusCode());
    }
}
