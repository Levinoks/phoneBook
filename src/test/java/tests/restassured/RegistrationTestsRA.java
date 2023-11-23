package tests.restassured;

import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTestsRA extends BaseRA{
    @Test
    public void positiveRegistrationStatusCodeTest() {
        // System.out.println("token from base" + token);
      Assert.assertEquals(userApi.getStatusCodeResponseReg(userForReg), 200);
       // System.out.println(userApi.getStatusCodeResponseReg(userForReg));
    }





    @Test
    public void testToken() {
        System.out.println("token - " + userApi.getTokenFromRegResponse(userForReg));
    }

    @Test
    public void negativeRegistrationTest(){      // any other error - 400
        userApi.setResponseRegNull();
        UserDtoLombok user1 = UserDtoLombok.builder()
                .username("qwer1@hh.e")
                .password("User12345")
                .build();
        int statusCode=userApi.getStatusCodeResponseReg(user1);
        userApi.setResponseLoginNull();
        Assert.assertEquals(statusCode, 400);


    }
    @Test
    public void negativeRegistrationTest1(){      // duplicate user - 409
        userApi.setResponseRegNull();
        int statusCode=userApi.getStatusCodeResponseReg(user);

        userApi.setResponseLoginNull();
       softAssert.assertEquals(statusCode, 409);

        softAssert.assertAll();

    }
}
