package org.example.DDT;

import org.example.endpoints.utils.UtilExcel;
import org.testng.annotations.Test;

public class LoginWithDDT {

@Test(dataProvider = "getData", dataProviderClass = UtilExcel.class)
public void testLoginData(String username, String password){
    System.out.println("UserName -"+ username);
    System.out.println("Password -"+ password);

}
}
