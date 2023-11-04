package org.example.curd;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
//import io.restassured.internal.RequestSpecificationImpl;
//import io.restassured.specification.RequestSpecification;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.endpoints.payloads.response.BookingRespons;
import org.testng.annotations.Test;



import static org.assertj.core.api.Assertions.*;

//import static org.junit.Assert.assertThat;

public class TC_CreateBooking extends BaseTest   {


 @Test(groups = {"stage","P0"})
 @Owner("Guru")
 @Severity(SeverityLevel.CRITICAL)
 @Description("Verify that Create Booking works and ID is generated")

 public void testCreateBooking() throws JsonProcessingException {
     //call the Request Block
     //Call the Payload
     //Call the Assertion Block

   requestSpecification.basePath(APIConstants.CREATE_BOOKING);
   response = requestSpecification
   .when().body(payloadManager.createPayload()).post();
   validatableResponse = response.then().log().all();
   validatableResponse.statusCode(200);
   //jsonPath = JsonPath.from(response.asString());
   //String bookingID = jsonPath.getString("bookingid");

   BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
   assertThat(bookingRespons.getBookingid().toString()).isNotEmpty().isNotNull();




 }

  /*@Test
    public void testCreateBooking2(){
        //call the Request Block
        //Call the Payload
        //Call the Assertion Block*/
}
