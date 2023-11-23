package tests.restassured;

import api.ContactsService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteContactTests extends BaseRA {

    @Test
    public void deleteContactTest() {
        contactsService.setResponseAddNewContactNull();
        String id = contactsService.getIDResponseAddNewContact(createNewContact(), token);
        contactsService.setResponseAddNewContactNull();
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteOneContact(token, id), 200);
        System.out.println(contactsService.getMessagePositiveResponseDeleteOneContact(token,id));
        softAssert.assertEquals(contactsService.getMessagePositiveResponseDeleteOneContact(token,id), "Contact was deleted!");
        contactsService.setNullResponseDeleteOneContact();
        softAssert.assertAll();;
    }

    @Test
    public void deleteAllContacts(){
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteAllContacts(token), 200);
        System.out.println(contactsService.getMessagePositiveResponseDeleteAllContacts(token));
        softAssert.assertEquals(contactsService.getMessagePositiveResponseDeleteAllContacts(token), "All contacts was deleted!");
        softAssert.assertAll();
    }
}
