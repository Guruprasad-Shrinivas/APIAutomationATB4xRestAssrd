package org.example.endpoints.utils;

import com.github.javafaker.Faker;

public class FakerUtil {

  static Faker faker;

  public static String getUsername(){
      faker = new Faker();
      String name = faker.name().fullName();
      return name;
  }


}
