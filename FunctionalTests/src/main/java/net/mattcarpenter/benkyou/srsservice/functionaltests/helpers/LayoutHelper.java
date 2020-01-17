package net.mattcarpenter.benkyou.srsservice.functionaltests.helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import net.mattcarpenter.benkyou.srsservice.functionaltests.constants.TestConstants;
import net.mattcarpenter.benkyou.srsservice.model.CreateLayoutRequest;
import net.mattcarpenter.benkyou.srsservice.model.LayoutFieldModel;

import java.util.Arrays;

@AllArgsConstructor
public class LayoutHelper {

    private String baseUrl;

    public Response createTestLayout() {
        RequestSpecification request = RestAssured.given();
        LayoutFieldModel field1 = LayoutFieldModel.builder().name("english").build();
        LayoutFieldModel field2 = LayoutFieldModel.builder().name("hiragana").build();
        CreateLayoutRequest createLayoutRequest = CreateLayoutRequest.builder()
                .frontHtml("front-html")
                .frontCss("front-css")
                .backHtml("back-html")
                .backCss("back-css")
                .name("foo")
                .fields(Arrays.asList(field1, field2))
                .build();
        request.body(createLayoutRequest);
        request.contentType(ContentType.JSON);
        return request.post(baseUrl + TestConstants.LAYOUTS_PATH);
    }
}
