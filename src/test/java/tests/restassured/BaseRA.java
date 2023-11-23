package tests.restassured;

import api.ContactsService;
import api.UserApi;
import dto.NewContactDto;
import dto.UserDtoLombok;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import utils.RandomUtils;

public class BaseRA {
    UserApi userApi = new UserApi();
    ContactsService contactsService = new ContactsService();
    String token = "";
    RandomUtils random=new RandomUtils();
    SoftAssert softAssert=new SoftAssert();

    UserDtoLombok user = UserDtoLombok.builder()
            .username("qwer1@hh.e")
            .password("User#12345")
            .build();
    UserDtoLombok userForReg = UserDtoLombok.builder()
            .username(random.generateEmail(4))
            .password("User#12345")
            .build();

    @BeforeSuite
    public void preCondApiRA() {
        token = userApi.getTokenFromLoginResponse(user);
    }

    public NewContactDto createNewContact(){
        String phoneNumber = random.generateStringDigits(10);
        NewContactDto contact1= NewContactDto.builder()
                .address("sdf")
                .description("sdfg")
                .email("tester20@yahoo.com")
                .id("0")
                .lastName("jfhdjk")
                .name("Before Update")
                .phone(phoneNumber)
                .build();
        return contact1;
    }public NewContactDto createNewContactForUpdate(String id){
        String phoneNumber = random.generateStringDigits(10);
        NewContactDto contact2= NewContactDto.builder()
                .address("sdf")
                .description("sdfg")
                .email("tester20@yahoo.com")
                .id(id)
                .lastName("jfhdjk")
                .name("After Update")
                .phone(phoneNumber)
                .build();
        return contact2;
    }
}
