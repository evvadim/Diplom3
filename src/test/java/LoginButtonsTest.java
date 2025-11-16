import api.CreateUserViaApi;
import api.DeleteUserViaApi;
import api.data.UserDataRequest;
import browser.AvailableBrowsers;
import browser.Browser;
import browser.BrowserFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ForgotPassword;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import java.time.Duration;

import static config.Config.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginButtonsTest {

    private WebDriver driver;
    private final Browser browser = new BrowserFactory().prepareBrowserNamed(AvailableBrowsers.CHROME);
    private String accessToken;

    @Before
    public void setUp() {

        // создадим пользователя через API

        UserDataRequest createUserRequest = new UserDataRequest(getEmail(), getUserPassword(), getUserName());
        CreateUserViaApi createUserViaApi = new CreateUserViaApi(createUserRequest);
        accessToken = createUserViaApi.fetchAccessToken();

        browser.driverManagerSetup();
        driver = browser.getNewDriver();

    }

    @Test
    @DisplayName("Test login user via Main Page. Login button")
    public void loginUserViaMainPageLoginButtonTest() {

        driver.get(getBaseURI());

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        String accessToken = loginProcess(driver);

        assertThat("Авторизация не пройдена, `accessToken` не получен", accessToken, notNullValue());

    }

    @Test
    @DisplayName("Test login user via Main Page. Account button")
    public void loginUserViaMainPageAccountButtonTest() {

        driver.get(getBaseURI());
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccountButton();

        String accessToken = loginProcess(driver);

        assertThat("Авторизация не пройдена, `accessToken` не получен", accessToken, notNullValue());

    }

    @Test
    @DisplayName("Test login user via Registration Page")
    public void loginUserViaRegistrationPageLoginButtonTest() {

        driver.get(String.format("%s%s", getBaseURI(), getRegisterPath()));
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginButton();

        String accessToken = loginProcess(driver);

        assertThat("Авторизация не пройдена, `accessToken` не получен", accessToken, notNullValue());

    }

    @Test
    @DisplayName("Test login user via ForgotPassword Page")
    public void loginUserViaForgotPasswordPageLoginButtonTest() {

        driver.get(String.format("%s%s", getBaseURI(), getForgotPasswordPath()));
        ForgotPassword forgotPassword = new ForgotPassword(driver);
        forgotPassword.clickLoginButton();

        String accessToken = loginProcess(driver);

        assertThat("Авторизация не пройдена, `accessToken` не получен", accessToken, notNullValue());

    }

    // вспомогательный метод авторизации
    String loginProcess(WebDriver driver) {

        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(loginPage.getLoginHeader()));

        loginPage.fillEmail(getEmail());
        loginPage.fillPassword(getUserPassword());
        loginPage.clickLoginButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']")));

        return (String) ((JavascriptExecutor) driver).executeScript("return window.localStorage.getItem('accessToken');");

    }

    @After
    public void tearDown() {
        new DeleteUserViaApi(accessToken).deleteUser();
        driver.quit();
    }
}
