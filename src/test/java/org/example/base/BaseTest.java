package org.example.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.AssertActions;
import org.example.endpoints.APIConstants;
import org.example.endpoints.modules.PayloadManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    public RequestSpecification requestSpecification;

    //public RequestSpecification getRequestSpecification()
    public AssertActions actions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;


    @BeforeMethod(alwaysRun = true)
    public void setUp(){
     //Reset the rest Assured Base URLs
     //Base URL
     //Content Type All

     payloadManager = new PayloadManager();
     actions = new AssertActions();
        /*payloadManager = new PayloadManager();
        actions = new AssertActions();
        requestSpecification = RestAssured.given()
                .given().baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();*/

//     requestSpecification = RestAssured.given()
//             .baseUri(APIConstants.BASE_URL)
//             .contentType(ContentType.JSON);

   requestSpecification = new RequestSpecBuilder().setBaseUri(APIConstants.BASE_URL)
           .addHeader("Content-Type","Application/Json")
           .build().log().all();

    }

    //Get token - Extract the token

    public String getToken() throws JsonProcessingException {
    requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL).basePath("/auth");

    String payload = payloadManager.setToken();
    response = requestSpecification.contentType(ContentType.JSON)
            .body(payload)
            .when().post();
    jsonPath = new JsonPath(response.asString());
    return jsonPath.getString("token");


    }
}
