package pl.tnt.tjb.test.serialization.restapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static pl.tnt.tjb.test.spec.MySpecBuilder.getPostmanEchoJsonRequestSpec;
import static pl.tnt.tjb.test.spec.MySpecBuilder.getPostmanEchoResponseSpec;

import java.io.File;
import org.testng.annotations.Test;
import pl.tnt.tjb.serialization.restapi.SimplePojo;

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
                .body("json.key1", equalTo("value1"), "json.key2", equalTo("value2"));
    }

    @Test
    public void shouldBeAbleToSendPojoAsJson() {
        SimplePojo simplePojo = new SimplePojo("value1", "value2");

        given(getPostmanEchoJsonRequestSpec())
                .body(simplePojo)
                .when()
                .post("/post")
                .then()
                .spec(getPostmanEchoResponseSpec())
                .assertThat()
                .body(
                        "json.key1",
                        equalTo(simplePojo.getKey1()),
                        "json.key2",
                        equalTo(simplePojo.getKey2()));
    }
}
