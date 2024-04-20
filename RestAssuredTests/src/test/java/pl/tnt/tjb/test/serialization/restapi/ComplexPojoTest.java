package pl.tnt.tjb.test.serialization.restapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static pl.tnt.tjb.test.spec.MySpecBuilder.getPostmanEchoJsonRequestSpec;
import static pl.tnt.tjb.test.spec.MySpecBuilder.getPostmanEchoResponseSpec;

import org.testng.annotations.Test;
import pl.tnt.tjb.serialization.restapi.ComplexPojo;

public class ComplexPojoTest {
    @Test
    public void shouldBeAbleToSendAnnotatedPojoAsJson() {
        ComplexPojo complexPojo = new ComplexPojo("value1", "value2", "value3", "value4");

        given(getPostmanEchoJsonRequestSpec())
                .body(complexPojo)
                .when()
                .post("/post")
                .then()
                .spec(getPostmanEchoResponseSpec())
                .assertThat()
                .body(
                        "json.key1",
                        equalTo(complexPojo.getKey1()),
                        "json.key2",
                        equalTo(complexPojo.getKey2()),
                        "json.key3",
                        equalTo(complexPojo.getKey3()),
                        "json.key4",
                        equalTo(complexPojo.getKey4()));
    }
}
