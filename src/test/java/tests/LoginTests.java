package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class LoginTests extends AppiumConfig {


    @Test
    public void loginSuccess() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
         boolean result = new AuthenticationScreen(driver).fillEmail("tmmmmmtttt@gmail.com")
                .fillPassword("Qwerty!12")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver).fillLoginRegistrationForm(Auth.builder()
                        .email("tmmmmmtttt@gmail.com")
                        .password("Qwerty!12").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);

    }

    @Test
    public void loginWrongEmail(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                .email("tmmmmmttttgmail.com")
                .password("Qwerty!12").build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }
    @Test
    public void loginWrongPassword(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("tmmmmmtttt@gmail.com")
                        .password("Qwerty").build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }

    @AfterMethod
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }

}
