package net.mattcarpenter.benkyou.srsservice.functionaltests.helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import net.mattcarpenter.benkyou.srsservice.functionaltests.constants.TestConstants;
import net.mattcarpenter.benkyou.srsservice.model.CardFieldModel;
import net.mattcarpenter.benkyou.srsservice.model.CreateCardRequest;

import java.util.Arrays;
import java.util.UUID;

@AllArgsConstructor
public class CardHelper {
    private String baseUrl;

    public Response createTestCard(UUID layoutId) {
        CardFieldModel field1 = CardFieldModel.builder().name("english").value("Japan").build();
        CardFieldModel field2 = CardFieldModel.builder().name("hiragana").value("にほん").build();
        CreateCardRequest createCardRequestBody = CreateCardRequest.builder()
                .fields(Arrays.asList(field1, field2))
                .layoutId(layoutId)
                .build();

        RequestSpecification createCardRequest = RestAssured.given();
        createCardRequest.body(createCardRequestBody);
        createCardRequest.contentType(ContentType.JSON);
        return createCardRequest.post(baseUrl + TestConstants.CARDS_PATH);
    }
}
