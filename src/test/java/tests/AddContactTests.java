package tests;

import dto.NewContactDto;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddContactTests extends BaseTests {
    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if (app.isPageUrlHome()) {
            app.getUserHelper().openLoginPage();
        }
        app.getUserHelper().fillLoginUserDtoLombok(user);

    }


    @AfterClass(alwaysRun = true)
    public void postconditions() {
        app.getUserHelper().logout();

    }

    @Test
    public void positiveAddContact() {
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
        app.getUserHelper().pause(3);
        app.getContactHelper().addNewContact(contactDto);
        Assert.assertTrue(app.getContactHelper().validateContactCreated(contactDto.getPhone()));

    }
}
