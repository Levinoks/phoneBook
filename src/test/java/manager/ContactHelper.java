package manager;

import dto.NewContactDto;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }


    By btnAddNewContact = By.xpath("//a[@href='/add']");
    By inputNameAddContact = By.xpath("//input[@placeholder='Name']");
    By inputLastNameAddContact = By.xpath("//input[@placeholder='Last Name']");
    By inputPhoneAddContact = By.xpath("//input[@placeholder='Phone']");
    By inputEmailAddContact = By.xpath("//input[@placeholder='email']");
    By inputAddressAddContact = By.xpath("//input[@placeholder='Address']");
    By inputDescrAddContact = By.xpath("//input[@placeholder='description']");

    By btnSaveAddContact = By.xpath("//button/b");
    By textH3ContactList = By.xpath("//h3");

    By btnRemoveContact = By.xpath("//button[text()='Remove']");


    public By getPhoneNumberInContactsLocator(String phone) {
        return By.xpath(String.format("//h3[contains(text(), '%s')]", phone));
    }

    public void addNewContact(NewContactDto contactDto) {
        clickBase(btnAddNewContact);
        typeTextBase(inputNameAddContact, contactDto.getName());
        typeTextBase(inputLastNameAddContact, contactDto.getLastName());
        typeTextBase(inputPhoneAddContact, contactDto.getPhone());
        typeTextBase(inputEmailAddContact, contactDto.getEmail());
        typeTextBase(inputAddressAddContact, contactDto.getAddress());
        typeTextBase(inputDescrAddContact, contactDto.getDescription());
        clickBase(btnSaveAddContact);

    }

    public boolean validateContactCreated(String phone) {
        return isElementByTextExistInList(textH3ContactList, phone);
    }

    public void openContactInfoByPhone(String phone) {
        clickBase(getPhoneNumberInContactsLocator(phone));
    }

    public void removeActiveContact() {
        clickBase(btnRemoveContact);
        WebElement button = driver.findElement(By.xpath("//button[text()='Remove']"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(button));


    }

    public void navigateToContactPage() {
        driver.navigate().to("https://telranedu.web.app/contacts");
    }

    public void scrollDown() {
        refresh();
        Actions action = new Actions(driver);
        action.moveByOffset(606, 773).build().perform();

    }

}
