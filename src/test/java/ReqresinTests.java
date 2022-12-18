import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresinTests {


    @Test
    public void userIsEmma() {

        when()
                .get("https://reqres.in/api/users/{id}", 3).
                then().
                log()
                .body()
                .statusCode(200).
                body("data.first_name", is("Emma"));
    }

    @Test
    public void CreateUser() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .statusCode(201)
                .body("job", is("leader"));

    }

    @Test
    public void UpdateUser() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/{id}", 2)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    public void DeleteUser() {
        given()
                .when()
                .delete("https://reqres.in/api/users/{id}", 2)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    public void LoginUser() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

}
