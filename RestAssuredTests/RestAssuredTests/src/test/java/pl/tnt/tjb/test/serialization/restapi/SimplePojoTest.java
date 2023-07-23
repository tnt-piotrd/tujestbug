package pl.tnt.tjb.test.serialization.restapi;

import org.testng.annotations.Test;
import pl.tnt.tjb.serialization.restapi.SimplePojo;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static pl.tnt.tjb.test.spec.MySpecBuilder.*;

public class SimplePojoTest {

    @Test
    public void shouldBeAbleToSendJsonAsFile() {
        File file = new File("src/test/resources/restapi/jsons/Simple.json");
        given(getPostmanEchoJsonRequestSpec())
                .body(file)
                .when()
                .post("/post")
                .then()
                .spec(getPostmanEchoResponseSpec())
                .assertThat()
                .body("json.key1", equalTo("value1"),
                        "json.key2", equalTo("value2"));
    }

    @Test
    public void shouldBeAbleToSendPojoAsJson() {
        SimplePojo simplePojo = new SimplePojo("value1", "value2");

        given(getPostmanEchoRequestSpec())
                .body(simplePojo)
                .when()
                .post("/post")
                .then()
                .spec(getPostmanEchoResponseSpec())
                .assertThat()
                .body("key1", equalTo(simplePojo.getKey1()),
                        "key2", equalTo(simplePojo.getKey2()));
    }
}
