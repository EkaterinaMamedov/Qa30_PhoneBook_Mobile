package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginTestsSecond extends AppiumConfig {
    @Test
    public void loginSuccess() {
        new AuthenticationScreen(driver)
                .fillEmail("tmmmmmtttt@gmail.com")
                .fillPassword("Qwerty!12")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginSuccessModel() {
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("tmmmmmtttt@gmail.com")
                        .password("Qwerty!12").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);

    }

    @Test
    public void loginSuccessModel2() {
        Assert.assertTrue(new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("tmmmmmtttt@gmail.com")
                        .password("Qwerty!12").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list"));

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
}
