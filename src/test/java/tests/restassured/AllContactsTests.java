package tests.restassured;

import org.testng.annotations.Test;

public class AllContactsTests extends BaseRA{
    @Test
    public void testAllContacts(){
        softAssert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(createNewContact(),token), 200);
        String id = contactsService.getIDResponseAddNewContact(createNewContact(), token);
        System.out.println("id created contact "+id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        softAssert.assertEquals(contactsService.getStatusCodeResponseGetAllContacts(token), 200);
        softAssert.assertTrue(contactsService.isIDInTheContactResponse(token, id));
        softAssert.assertAll();
    }
}
