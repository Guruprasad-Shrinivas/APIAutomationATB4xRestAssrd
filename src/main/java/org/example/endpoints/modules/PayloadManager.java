package org.example.endpoints.modules;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.endpoints.payloads.request.Booking;
import org.example.endpoints.payloads.request.Bookingdates;
import org.example.endpoints.payloads.response.BookingRespons;

public class PayloadManager {

   ObjectMapper objectMapper;
   public String createPayload() throws JsonProcessingException {
      objectMapper = new ObjectMapper();
      Booking booking = new Booking();
      booking.setFirstname("Guru");
      //booking.setFirstname(FakerUtil.getUsername());
      booking.setLastname("Prasad");
      booking.setTotalprice(123);
      booking.setDepositpaid(true);
      booking.setAdditionalneeds("BreakFast");

      Bookingdates bookingdates = new Bookingdates();
      bookingdates.setCheckin("2022-01-01");
      bookingdates.setCheckout("2022-01-01");
      booking.setBookingdates(bookingdates);

      String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
      return payload;


   }

   public BookingRespons JsonToObject(String jsonString) throws JsonProcessingException {
      objectMapper = new ObjectMapper();
      BookingRespons bookingRespons = objectMapper.readValue(jsonString,BookingRespons.class);
      return bookingRespons;


   };
}
