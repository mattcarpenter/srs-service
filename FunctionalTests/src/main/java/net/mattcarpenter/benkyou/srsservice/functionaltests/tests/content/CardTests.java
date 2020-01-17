package net.mattcarpenter.benkyou.srsservice.functionaltests.tests.content;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.mattcarpenter.benkyou.srsservice.functionaltests.constants.TestConstants;
import net.mattcarpenter.benkyou.srsservice.functionaltests.utils.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.*;

public class CardTests extends TestBase {

    @BeforeClass
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void createCard_createsCard() {
        Response createLayoutResponse = layoutHelper.createTestLayout();
        UUID layoutId = UUID.fromString(createLayoutResponse.getBody().path("id"));
        Response createCardResponse = cardHelper.createTestCard(layoutId);

        assertTestCard(createCardResponse, layoutId.toString());
    }

    @Test
    public void getCard_getsCard() {
        Response createLayoutResponse = layoutHelper.createTestLayout();
        UUID layoutId = UUID.fromString(createLayoutResponse.getBody().path("id"));
        Response createCardResponse = cardHelper.createTestCard(layoutId);
        String cardId = createCardResponse.body().path("id").toString();

        Response getCardResponse = RestAssured.given().get(
                getProperty(TestConstants.BASE_URL) + String.format(TestConstants.CARDS_RESOURCE_PATH_TEMPLATE, cardId)
        );

        assertTestCard(getCardResponse, layoutId.toString());
    }

    private void assertTestCard(Response response, String layoutId) {
        response.then().assertThat()
                .body("layoutId", equalTo(layoutId))
                .body("fields", hasItems(hasEntry("name", "english"), hasEntry("value", "Japan")))
                .body("fields", hasItems(hasEntry("name", "hiragana"), hasEntry("value", "にほん")));
    }
}
