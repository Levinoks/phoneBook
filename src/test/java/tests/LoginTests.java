package tests;

import data.DataProviderLogin;
import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests {

    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if(app.isPageUrlHome()) {
            app.getUserHelper().openLoginPage();

        }
    }

    @AfterMethod(alwaysRun = true)
    public void preconditionsBeforeMethod() {
        preconditionForLoginAndRegTests();
    }
    UserDTOWith userDTOWith = new UserDTOWith()
            .withEmail("qwer1@hh.e")
            .withPassword("User#12345");
    UserDTO userDTO = new UserDTO("qwer1@hh.e", "User#12345");

    @Test(groups = {"smoke"})
    public void positiveLoginUserDto() {
        logger.info("logger info: start test positiveLoginUserDto");
        logger.info(String
                .format("in the next function: fill email input with email: %s and with the password: %s and click on button login",
                        userDTO.getEmail(), userDTO.getPassword()));
        app.getUserHelper().fillLoginUserDto(userDTO);
        logger.info("validation by assertTrue, that contacts link displays in the menu");
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test
    public void positiveLoginUserDtoWith() {
        app.getUserHelper().fillLoginUserDtoWith(userDTOWith);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

   // @Test(groups = {"regression"}, dataProvider="positiveDataLogin", dataProviderClass= DataProviderLogin.class)
   @Test(dataProvider = "loginCSV",dataProviderClass = DataProviderLogin.class)
    public void positiveLoginUserDtoLombok(UserDtoLombok userDP) {

        app.getUserHelper().fillLoginUserDtoLombok(userDP);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test(groups = {"smoke"}, dataProvider="negativePasswordDataLogin", dataProviderClass = DataProviderLogin.class)
    public void negativeWrongPasswordWrongSymbol(UserDtoLombok userDP) {

        app.getUserHelper().fillLoginUserDtoLombok(userDP);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrect()); // Wrong email or password
    }

    @Test
    public void negativeWrongPasswordNoLetters() {
        UserDtoLombok user = UserDtoLombok.builder()
                .username("qwer1@hh.e")
                .password("@123123456")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrect()); // Wrong email or password
    }

    @Test
    public void negativeWrongPasswordNoDigits() {
        UserDtoLombok user = UserDtoLombok.builder()
                .username("qwer1@hh.e")
                .password("User#User")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrect()); // Wrong email or password
    }


}
