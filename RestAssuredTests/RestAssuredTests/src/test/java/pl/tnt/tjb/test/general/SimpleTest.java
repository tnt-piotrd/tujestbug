package pl.tnt.tjb.test.general;

import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SimpleTest {

    private static final String HEADER_VALUE = "tu_jest_bug";

    @Test
    public void getFirstUserDataTest(){
        given()
            .when()
            .get("https://reqres.in/api/users/1")
            .then()
            .statusCode(200);
    }

    @Test
    public void verifySecondUserLastNameTest(){
        given()
            .baseUri("https://reqres.in/api")
            .pathParam("userId", 2)
            .when()
            .get("/users/{userId}")
            .then()
            .body("data.last_name", equalTo("Weaver"));
    }

    @Test
    public void verifyHeadersAreSent(){
        given()
            .config(config().encoderConfig(EncoderConfig.encoderConfig()
            .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
            .baseUri("https://postman-echo.com")
            .header("myheader", HEADER_VALUE)
            .when()
            .log().all()
            .post("/post")
            .then()
            .log().all()
            .statusCode(200)
            .body("headers.myheader", equalTo(HEADER_VALUE));
    }
}
