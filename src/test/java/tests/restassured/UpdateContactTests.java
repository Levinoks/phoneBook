package tests.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateContactTests extends BaseRA{
    @Test
    public void updateContactTest(){
        String id = contactsService.getIDResponseAddNewContact(createNewContact(), token);
     softAssert.assertEquals
                (contactsService.getStatusCodeResponseUpdateContacts(createNewContactForUpdate
                        (id), token), 200);
    softAssert.assertEquals(contactsService.getMessagePositiveResponseUpdateContact(createNewContactForUpdate(id), token), "Contact was updated");
        System.out.println(contactsService.getMessagePositiveResponseUpdateContact(createNewContactForUpdate(id), token));

     softAssert.assertAll();
    }
}
