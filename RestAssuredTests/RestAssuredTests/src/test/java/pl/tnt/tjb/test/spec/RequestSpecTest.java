package pl.tnt.tjb.test.spec;

import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class RequestSpecTest {
    private static final String HEADER_VALUE = "tu_jest_bug";
    private static final String SECOND_HEADER_VALUE = "tu_jest_bug2";
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @BeforeMethod
    public void setup(){
        requestSpecification = given()
                .config(config().encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .baseUri("https://postman-echo.com")
                .header("myHeader", HEADER_VALUE)
                .log().all();

        responseSpecification = expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("headers.myheader", equalTo(HEADER_VALUE))
                .log().all();
    }

    @Test
    public void postmanShouldEchoHeaders(){
        given(requestSpecification)
            .when()
                .post("/post")
            .then()
                .spec(responseSpecification);
    }

    @Test
    public void postmanShouldEchoTwoHeaders(){
        given(requestSpecification)
                .header("additionalheader", SECOND_HEADER_VALUE)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("headers.myheader", equalTo(HEADER_VALUE),
                        "headers.additionalheader", equalTo(SECOND_HEADER_VALUE));
    }
}
