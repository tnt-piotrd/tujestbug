package pl.tnt.tjb.test.spec;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestSpecBuilderTest {
    private static final String HEADER_VALUE = "tu_jest_bug";
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @BeforeMethod
    public void setup() {
        requestSpecification =
                new RequestSpecBuilder()
                        .setBaseUri("https://postman-echo.com")
                        .setConfig(
                                config().encoderConfig(
                                                EncoderConfig.encoderConfig()
                                                        .appendDefaultContentCharsetToContentTypeIfUndefined(
                                                                false)))
                        .addHeader("myHeader", HEADER_VALUE)
                        .log(LogDetail.ALL)
                        .build();

        responseSpecification =
                new ResponseSpecBuilder()
                        .expectStatusCode(200)
                        .expectContentType(ContentType.JSON)
                        .expectBody("headers.myheader", equalTo(HEADER_VALUE))
                        .log(LogDetail.ALL)
                        .build();
    }

    @Test
    public void postmanShouldEchoHeaders() {
        given(requestSpecification).when().post("/post").then().spec(responseSpecification);
    }
}
