import browser.AvailableBrowsers;
import browser.Browser;
import browser.BrowserFactory;
import io.qameta.allure.junit4.DisplayName;
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

import static browser.AvailableBrowsers.*;
import static config.Config.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class PasswordFieldValidationParamTests {

    private WebDriver driver;

    private final String password;
    private final boolean isValid;
    private final AvailableBrowsers browserDriver;

    private final int minLength = 6;

    public PasswordFieldValidationParamTests(String password, AvailableBrowsers availableBrowsers) {
        this.password = password;
        this.browserDriver = availableBrowsers;

        this.isValid = password.length() >= minLength;
    }

    @Parameterized.Parameters(name = "Testing validation password field. Set `{0}`")
    public static Object[][] getPasswords() {
        return new Object[][] {
                {getUserPassword().substring(0, 2), CHROME},
                {getUserPassword().substring(0, 4), CHROME},
                {getUserPassword().substring(0, 5), CHROME},
                {getUserPassword().substring(0, 6), CHROME},
                {getUserPassword().substring(0, 7), CHROME},
                {getUserPassword().substring(0, 9), CHROME},
        };
    }

    @Before
    public void setUp() {

        Browser browser = new BrowserFactory().prepareBrowserNamed(browserDriver);
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
