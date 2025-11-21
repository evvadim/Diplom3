package pages;

import api.data.UserDataRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    // локатор заголовка Вход
    private final By loginHeader = By.xpath(".//div[@class='Auth_login__3hAey']");

    // локатор поля Email
    private final By emailField = By.xpath(".//div[contains(@class,'input')]/label[text()='Email']/parent::div/input");

    // локатор поля Пароль
    private final By passwordField = By.xpath(".//div[contains(@class,'input')]/label[text()='Пароль']/parent::div/input");

    // локатор кнопки Войти
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    // локатор кнопки Зарегистрироваться
    private final By registerButton = By.xpath(".//a[@class='Auth_link__1fOlj'][text()='Зарегистрироваться']");

    // локатор кнопки Восстановить пароль
    private final By forgotPasswordButton = By.xpath(".//a[@class='Auth_link__1fOlj'][text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // вспомагательный метод заполнения текстовых полей
    public void fillInputTextAt(By locator, String inputText) {
        WebElement input = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", input);
        input.sendKeys(inputText);
    }

    public void clickAtElement(WebElement button) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    public void fillEmail(String string) {
        fillInputTextAt(emailField, string);
    }

    public void fillPassword(String string) {
        fillInputTextAt(passwordField, string);
    }

    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        clickAtElement(button);
    }

    public void clickRegisterButton() {
        WebElement button = driver.findElement(registerButton);
        clickAtElement(button);
    }

    public void clickForgotPasswordButton() {
        WebElement button = driver.findElement(forgotPasswordButton);
        clickAtElement(button);
    }

    // вспомогательный метод авторизации
    public String loginProcess(UserDataRequest loginDataRequest) {

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(getLoginHeader()));

        fillEmail(loginDataRequest.getEmail());
        fillPassword(loginDataRequest.getPassword());
        clickLoginButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']")));

        return (String) ((JavascriptExecutor) driver).executeScript("return window.localStorage.getItem('accessToken');");

    }

    public WebElement getLoginHeader() {
        return driver.findElement(loginHeader);
    }

    public WebElement getEmailField() {
        return driver.findElement(emailField);
    }

    public WebElement getPasswordField() {
        return driver.findElement(passwordField);
    }

    public WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }

    public WebElement getRegisterButton() {
        return driver.findElement(registerButton);
    }

    public WebElement getForgotPasswordButton() {
        return driver.findElement(forgotPasswordButton);
    }

}
