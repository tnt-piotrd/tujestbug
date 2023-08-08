package pl.tnt.tjb.test.serialization.restapi;

import org.testng.annotations.Test;
import pl.tnt.tjb.serialization.restapi.reqres.ReqResResponsePojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static pl.tnt.tjb.test.spec.MySpecBuilder.getReqresInRequestSpec;
import static pl.tnt.tjb.test.spec.MySpecBuilder.getReqresResponseSpec;

public class ReqRestPojoTest {
    @Test
    public void shouldBeAbleToMapJsonToPojo() {
        ReqResResponsePojo pojo = given(getReqresInRequestSpec())
                .when()
                .get("/users/2")
                .then()
                .spec(getReqresResponseSpec())
                .extract().as(ReqResResponsePojo.class);
        assertThat(pojo.getData().getFirstName(), equalTo("Janet"));
    }
}
