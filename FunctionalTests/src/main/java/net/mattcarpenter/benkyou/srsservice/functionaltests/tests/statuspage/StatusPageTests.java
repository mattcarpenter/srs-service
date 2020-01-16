package net.mattcarpenter.benkyou.srsservice.functionaltests.tests.statuspage;

import net.mattcarpenter.benkyou.srsservice.functionaltests.utils.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class StatusPageTests extends TestBase {

    @Test
    public void statusPage_reportsHealthy() {
        given()
                .when()
                    .get(getProperty("baseUrl") + "/status")
                .then()
                    .assertThat()
                    .body("status", equalTo("healthy"));
    }
}
