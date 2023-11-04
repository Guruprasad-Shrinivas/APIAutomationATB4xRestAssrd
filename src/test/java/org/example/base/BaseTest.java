package org.example.base;

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

public class BaseTest {

    public RequestSpecification requestSpecification;

    //public RequestSpecification getRequestSpecification()
    public AssertActions actions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;


    @BeforeMethod
    public void setUp(){
     //Reset the rest Assured Base URLs
     //Base URL
     //Content Type All

     payloadManager = new PayloadManager();
     actions = new AssertActions();

//     requestSpecification = RestAssured.given()
//             .baseUri(APIConstants.BASE_URL)
//             .contentType(ContentType.JSON);

   requestSpecification = new RequestSpecBuilder().setBaseUri(APIConstants.BASE_URL)
           .addHeader("Content-Type","Application/Json")
           .build().log().all();

    }
}
