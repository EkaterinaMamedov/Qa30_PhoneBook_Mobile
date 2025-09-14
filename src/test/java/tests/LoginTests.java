package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {


    @Test
    public void loginSuccess() {
        boolean result = new SplashScreen(driver)
                .checkCurrentVersion("Version 1.0.0")
                .fillEmail("tmmmmmtttt@gmail.com")
                .fillPassword("Qwerty!12")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel() {
        boolean result = new SplashScreen(driver)
                .checkCurrentVersion("Version 1.0.0")
                .fillLoginRegistrationForm(Auth.builder()
                        .email("tmmmmmtttt@gmail.com")
                        .password("Qwerty!12").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);

    }


}
