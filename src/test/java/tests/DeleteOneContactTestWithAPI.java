package tests;

import dto.NewContactDto;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteOneContactTestWithAPI extends BaseTests {
    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if (app.isPageUrlHome()) {
            app.getUserHelper().openLoginPage();
        }
        app.getUserHelper().fillLoginUserDtoLombok(user);

    }


    @AfterClass(alwaysRun = true)
    public void postconditions() throws InterruptedException {
        app.getUserHelper().logout();
        Thread.sleep(500);


    }

    @Test
    public void positiveCreatedContactDelete() {
        String phone = random.generateStringDigits(10);
        System.out.println("phone for the new contact - " + phone);
        logger.info("phone for the new contact - " + phone);
        NewContactDto contactDto = NewContactDto.builder()
                .address("hdsbvhjs")
                .description("fdvds")
                .email("gfhdjs@gh.cv")
                .lastName("vfdsdgh")
                .name("ddksjk")
                .phone(phone)
                .build();

        softAssert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(contactDto, token), 200);
        app.getContactHelper().openContactInfoByPhone(phone);
        app.getContactHelper().removeActiveContact();
        softAssert.assertFalse(app.getContactHelper().validateContactCreated(phone));
        softAssert.assertAll();

    }
}
