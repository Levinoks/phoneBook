package tests;

import data.DataProviderLogin;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTests {

    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if (app.isPageUrlHome()) {
            app.getUserHelper().openLoginPage();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void preconditionsBeforeMethod() {
        preconditionForLoginAndRegTests();
    }

    @Test(groups = {"smoke", "regression"})
    public void positiveRegistration() {
        String email = random.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .username(email)
                .password("User#12345")
                .build();
        app.getUserHelper().fillRegUserDtoLombok(user);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test(enabled = false, dataProvider = "loginCSV", dataProviderClass = DataProviderLogin.class)
    public void positiveRegistration(UserDtoLombok userDP) {

        app.getUserHelper().fillRegUserDtoLombok(userDP);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test
    public void negativeRegNoSymbol() {
        String email = random.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .username(email)
                .password("123456Aa")
                .build();
        app.getUserHelper().fillRegUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrectReg());
    }

    @Test
    public void negativeRegNoLetters() {
        String email = random.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .username(email)
                .password("12345699#")
                .build();
        app.getUserHelper().fillRegUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrectReg());
    }

    @Test(groups = {"regression"})
    public void negativeRegNoDigits() {
        String email = random.generateEmail(7);
        UserDtoLombok user = UserDtoLombok.builder()
                .username(email)
                .password("Agshsjsks#")
                .build();
        app.getUserHelper().fillRegUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrectReg());
    }
}
