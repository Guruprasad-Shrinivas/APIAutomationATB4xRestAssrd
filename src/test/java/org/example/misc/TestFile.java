package org.example.misc;

import com.codoid.products.exception.FilloException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.base.BaseTest;
import org.example.endpoints.utils.FillowUtil;
import org.testng.annotations.Test;


public class TestFile {


        @Test
        public void postRequest(){
            String payload ="{\n" +
                    "    \"username\" : \"admin\",\n" +
                    "    \"password\" : \"password123\"\n" +
                    "}";

            RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)

                .when().body(payload).post()

                .then().log().all().statusCode(200);
    }

    @Test
    public void testFillow() throws FilloException {
        String BASE_URL = FillowUtil.fetchDataFromXLSX("Sheet1","baseurl","value");
        System.out.println(BASE_URL);

    }
}
