package pl.tnt.tjb.test.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.config;
import static org.hamcrest.CoreMatchers.equalTo;

public class MySpecBuilder {
    private static final String HEADER_VALUE = "tu_jest_bug";

    public static RequestSpecification getPostmanEchoJsonRequestSpec() {
        return getPostmanEchoRequestSpec()
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification getPostmanEchoRequestSpec() {
        return getPostmanEchoRequestSpec(Map.of("myHeader", HEADER_VALUE));
    }

    public static RequestSpecification getPostmanEchoRequestSpec(Map<String, String> headers) {
        return new RequestSpecBuilder()
                .setBaseUri("https://postman-echo.com")
                .setConfig(config().encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .addHeaders(headers)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getReqresInRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getPostmanEchoResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectBody("headers.myheader", equalTo(HEADER_VALUE))
                .log(LogDetail.ALL)
                .build();
    }
}
