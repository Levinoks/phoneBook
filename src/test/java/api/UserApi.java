package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import dto.MessageResponseDTO;
import dto.UserDtoLombok;

import static com.jayway.restassured.RestAssured.given;

public class UserApi extends BaseApi{
    Response responseLogin =null;
    Response responseReg =null;
    private Response loginRequest(UserDtoLombok user){
       // responseLogin=given().body(user)
        System.out.println("----------------------- loginRequest method run");
        responseLogin = given()
//                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(baseUrl+"/v1/user/login/usernamepassword")
                .thenReturn();
        return responseLogin;
    }
    private  Response registrationRequest(UserDtoLombok user){
        responseReg=given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(baseUrl+"/v1/user/registration/usernamepassword")
                .thenReturn();
        return responseReg;
    }
    public void setResponseLoginNull(){
        responseLogin=null;
    }
    public int getStatusCodeResponseLogin(UserDtoLombok user){
     if (responseLogin==null){
         loginRequest(user);
     }
     return responseLogin.getStatusCode();
    }

    public String getTokenFromLoginResponse(UserDtoLombok user){
        if (responseLogin==null){
           responseLogin= loginRequest(user);
        }
        return responseLogin.getBody().as(AuthResponseDTO.class).getToken();
    }
    public void setResponseRegNull(){
        responseReg=null;
    }
    public int getStatusCodeResponseReg(UserDtoLombok user){
     if (responseReg==null){
         registrationRequest(user);
     }
     return responseReg.getStatusCode();
    }

    public String getTokenFromRegResponse(UserDtoLombok user){
        if (responseReg==null){
            responseReg= registrationRequest(user);
        }
        return responseReg.getBody().as(AuthResponseDTO.class).getToken();
    }
    public String getErrorMessageFromRegResponse(UserDtoLombok user){
        if (responseReg==null){
            responseReg= registrationRequest(user);
        }
        return responseReg.getBody().as(MessageResponseDTO.class).getMessage();
    }
}
