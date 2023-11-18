package tests.okhttp;

import com.google.gson.Gson;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import dto.UserDtoLombok;
import groovy.json.StringEscapeUtils;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomUtils;

import java.io.IOException;

public class RegistrationTestOkHTTP {
    RandomUtils random=new RandomUtils();
    UserDtoLombok user = UserDtoLombok.builder()
            .username(random.generateEmail(5))
            .password("User#12345")
            .build();
    public static final MediaType JSON = MediaType.get("application/json");
    Gson gson = new Gson();
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://contactapp-telran-backend.herokuapp.com";

    @Test
    public void regPositive() {
        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response == null) {
            Assert.fail("got null response");
        } else if (response.isSuccessful()) {// return 200 and token
            String responseJson;
            try {
                responseJson = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AuthResponseDTO authResponseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
            System.out.println(authResponseDTO.getToken());
            System.out.println(response.code());
            System.out.println(response.message());
            Assert.assertEquals(response.code(), 200, "response not 200");
        } else {
            System.out.println(response.code() + "error");
            Assert.fail("response is not successful");

        }
    }


    @Test
    public void regNegativeDuplicateUser() {
        UserDtoLombok userNegative = UserDtoLombok.builder()
                .username("qwer1@hh.e")
                .password("User#12345")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(userNegative), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response == null) {
            Assert.fail("got null response");
        } else if (response.isSuccessful()) {// return 200 and token
            Assert.fail("got response with status code " + response.code());

        } else {
            String responseJson;
            try {
                responseJson = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

           ErrorDTO errorDTO = new Gson().fromJson(this.removeQuotes(responseJson), ErrorDTO.class);

            System.out.println(response.code());
            System.out.println(response.message());
            System.out.println("string error: " + errorDTO.getError());
            System.out.println("int status: " + errorDTO.getStatus());
            System.out.println("string message: " + errorDTO.getMessage());
            Assert.assertEquals(response.code(), 409, "response not 409");
            Assert.assertEquals(errorDTO.getMessage(), "User already exists");


        }
    }

    public String removeQuotes(String responseJson) {
        String noQuotes = responseJson.replaceAll("\"\"", "");
        return StringEscapeUtils.unescapeJava(noQuotes);
    }


}
