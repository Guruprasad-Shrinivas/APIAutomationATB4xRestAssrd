package org.example.curd.INT;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.endpoints.payloads.request.Booking;
import org.example.endpoints.payloads.response.BookingRespons;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assert;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.assertj.core.api.Assertions.*;
public class TC_Integration extends BaseTest {
    String token;
    String bookingIdPojo;
    String bookingId;

    //Get token - Extract the token
//@Test(groups = "P0")
//public String getToken(){
//
//      assertThat("Guru").isEqualTo("Guru");
//    return null;
//}
    private static final Logger log = LogManager.getLogger(TC_Integration.class);

    //Create booking
    @Test(groups = "P0")
    public void testCreateBooking() throws JsonProcessingException {
        token = getToken();
        assertThat(token).isNotNull().isNotEmpty();

        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(200);
        // Direct Extraction from json Path
        bookingId = jsonPath.getString("bookingid");
        // Booking Response Class
        BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
        bookingIdPojo = bookingRespons.getBookingid().toString();
        log.info("This is My Booking ID" + bookingIdPojo);
        assertThat(bookingId).isNotNull().isNotEmpty();
        //assertThat("Guru").isEqualTo("Guru");
    }


    //Update the booking with Token and Booking ID
// @Test(groups = "P0", dependsOnMethods = {"testCreateBooking"})
// public void testCreateAndUpdateBooking() throws JsonProcessingException {
//     System.out.println("testCreateBooking ->"+token);
//     System.out.println("testCreateBooking ->"+bookingId);
//     System.out.println("testCreateBooking ->"+bookingIdPojo);
//     //assertThat("Guru").isEqualTo("Guru");
//
//     System.out.println("Guru -" +bookingId);
//     requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" +  bookingId);
//     response = RestAssured.given().spec(requestSpecification).cookie("token",token)
//             .when().body(payloadManager.updatedPayload()).put();
//     validatableResponse = response.then().log().all();
//     //validatableResponse.body("firstname", Matchers.is("Lucky"));
//
//     Booking bookingRespons = payloadManager.JsonToObjectPUT(response.asString());
//     assertThat(bookingRespons.getFirstname()).isEqualTo("Lucky").isNotNull();
//     assertThat(bookingRespons.getLastname()).isNotNull();
//     assertThat(bookingRespons.getDepositpaid()).isNotN   ull();
    @Test(groups = "P0", dependsOnMethods = {"testCreateBooking"})
    public void testCreateAndUpdateBooking() throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token", token)
                .when().body(payloadManager.updatedPayload()).put();
        validatableResponse = response.then().log().all();
        //validatableResponse.body("firstname", Matchers.is("Lucky"));

        Booking bookingRespons = payloadManager.JsonToObjectPUT(response.asString());
        assertThat(bookingRespons.getFirstname()).isEqualTo("Lucky").isNotNull();
        assertThat(bookingRespons.getLastname()).isNotNull();
        assertThat(bookingRespons.getDepositpaid()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckin()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckout()).isNotEmpty();

    }

    //Delete
    @Test(groups = "P0", dependsOnMethods = {"testCreateAndUpdateBooking"})
    public void testDeleteCreateBooking() {
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId).cookie("token", token);
        ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123")
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);

        System.out.println("testCreateAndUpdateBooking ->" + token);
        System.out.println("testCreateAndUpdateBooking ->" + bookingId);
        System.out.println("testCreateAndUpdateBooking ->" + bookingIdPojo);
        assertThat("Guru").isEqualTo("Guru");
    }

    @Test(groups = "P0", dependsOnMethods = {"testDeleteCreateBooking"})
    public void testDeleteBookingByGet() {
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
    }
}