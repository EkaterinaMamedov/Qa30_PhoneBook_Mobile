package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class AddNewContactTests extends AppiumConfig {


    @BeforeClass
    public void preCondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("tmmmmmtttt@gmail.com")
                        .password("Qwerty!12").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
    }

    @Test
    public void createNewContactSuccess() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("1234567" + i)
                .address("haifa")
                .description("best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName()
                );

    }

    @Test

    public void createNewContactWithEmptyName() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("")
                .lastName("wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("1234567" + i)
                .address("haifa")
                .description("best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("{name=must not be blank}");
                ;

    }

    @Test
    public void createNewContactSuccessReg() {

    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }
}
