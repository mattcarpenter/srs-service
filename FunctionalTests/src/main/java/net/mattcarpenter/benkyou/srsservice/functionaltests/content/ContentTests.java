package net.mattcarpenter.benkyou.srsservice.functionaltests.content;

import net.mattcarpenter.benkyou.srsservice.functionaltests.utils.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class ContentTests extends TestBase {

    @Test
    public void Test(){
        given()
                .when()
                    .get(getProperty("baseUrl") + "/status")
                .then()
                    .assertThat()
                    .body("status", equalTo("healthy"));
    }
}
