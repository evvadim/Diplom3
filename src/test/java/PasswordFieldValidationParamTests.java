import browser.Browser;
import browser.BrowserFactory;
import config.Config;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterPage;

import java.time.Duration;

import static config.Config.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class PasswordFieldValidationParamTests {

    private WebDriver driver;

    private final String password;
    private final boolean isValid;

    private final int minLength = 6;
    private final String browser = Config.getBrowser();

    public PasswordFieldValidationParamTests(int passwordLength) {
        this.password = new Faker().internet().password(passwordLength, passwordLength, true);
        this.isValid = password.length() >= minLength;
    }

    @Parameterized.Parameters(name = "Testing validation password field. Password length `{0}`")
    public static Object[][] getPasswords() {
        return new Object[][] {
                {2},
                {4},
                {5},
                {6},
                {7},
                {9},
        };
    }

    @Before
    public void setUp() {

        Browser browser = new BrowserFactory().prepareBrowserNamed(this.browser);
        browser.driverManagerSetup();
        driver = browser.getNewDriver();

    }


    @Test
    @DisplayName("Validation password test")
    public void validationTest() {

        driver.get(String.format("%s%s", getBaseURI(), getRegisterPath()));

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillPassword(password);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(registerPage.getNameField()));
        registerPage.clickNameField();

        assertThat("Validation Error is not visible", registerPage.getValidationErrorMessage().isEmpty() == isValid);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
