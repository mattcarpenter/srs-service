package net.mattcarpenter.benkyou.srsservice.functionaltests.tests.content;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.mattcarpenter.benkyou.srsservice.functionaltests.constants.TestConstants;
import net.mattcarpenter.benkyou.srsservice.functionaltests.utils.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class LayoutTests extends TestBase {

    @BeforeClass
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void createLayout_createsLayout() {
        Response createTestLayoutResponse = layoutHelper.createTestLayout();
    }

    @Test
    public void getLayout_getsLayout() {

        // create a test layout and obtain its id
        Response createResponse = layoutHelper.createTestLayout();
        String layoutId = createResponse.getBody().path("id");

        // call get-layout endpoint with the id of the newly minted layout
        Response getResponse = getTestLayout(layoutId);

        // assertions
        assertTestLayout(getResponse);
    }

    @Test
    public void getLayout_invalidLayoutId() {
        Response response = getTestLayout("fake-layout-id");
        response.then().assertThat().statusCode(400); // todo - assert errors
    }

    @Test
    public void deleteLayout_deletesLayout() {

        // create a test layout and obtain its id
        Response createResponse = layoutHelper.createTestLayout();
        String layoutId = createResponse.getBody().path("id");

        // delete the layout
        deleteTestLayout(layoutId).then().assertThat().statusCode(200);

        // expect get to fail
        getTestLayout(layoutId).then().assertThat().statusCode(404); // todo - assert errors
    }

    private void assertTestLayout(Response response) {
        response.then().assertThat()
                .body("frontHtml", equalTo("front-html"))
                .body("frontCss", equalTo("front-css"))
                .body("backHtml", equalTo("back-html"))
                .body("backCss", equalTo("back-css"))
                .body("name", equalTo("foo"))
                .body("fields", hasItems(hasEntry("name", "english")))
                .body("fields", hasItems(hasEntry("name", "hiragana")));
    }

    private Response deleteTestLayout(String layoutId) {
        return RestAssured.given().delete(
                getProperty(TestConstants.BASE_URL) + String.format(TestConstants.LAYOUTS_RESOURCE_PATH_TEMPLATE, layoutId));
    }

    private Response getTestLayout(String layoutId) {
        return RestAssured.given().get(
                getProperty(TestConstants.BASE_URL) + String.format(TestConstants.LAYOUTS_RESOURCE_PATH_TEMPLATE, layoutId));
    }
}
