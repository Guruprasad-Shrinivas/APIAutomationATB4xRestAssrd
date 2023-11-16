package org.example.curd;


import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

public class TC_Integration {

//Get token - Extract the token
@Test(groups = "P0")
public void Test1getToken(){
      assertThat("Guru").isEqualTo("Prasad");
  }

 //Create booking
 @Test(groups = "P0")
 public void test2reateBooking(){
     assertThat("Guru").isEqualTo("Prasad");
 }

 //Update the booking with Token and Booking ID
 @Test(groups = "P0")
 public void test3CreateAndUpdateBooking(){
     assertThat("Guru").isEqualTo("Prasad");
 }

 //Delete
 @Test(groups = "P0")
 public void test4DeleteCreateBooking(){
     assertThat("Guru").isEqualTo("Prasad");
 }
}
