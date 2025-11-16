package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegisterPage {

    private final WebDriver driver;

    // локатор поля Имя
    private final By nameField = By.xpath(".//div[contains(@class,'input')]/label[text()='Имя']/parent::div/input");

    // локатор поля Email
    private final By emailField = By.xpath(".//div[contains(@class,'input')]/label[text()='Email']/parent::div/input");

    // локатор поля Пароль
    private final By passwordField = By.xpath(".//div[contains(@class,'input')]/label[text()='Пароль']/parent::div/input");

    // локатор текста ошибка валидации
    private final By validationErrorMessage = By.xpath(".//p[@class='input__error text_type_main-default']");

    // локатор кнопки Зарегистрироваться
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    // локатор кнопки Войти
    private final By loginButton = By.xpath(".//a[@class='Auth_link__1fOlj']");

    public RegisterPage(WebDriver driver) {
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

    public void fillName(String string) {
        fillInputTextAt(nameField, string);
    }

    public void fillEmail(String string) {
        fillInputTextAt(emailField, string);
    }

    public void fillPassword(String string) {
        fillInputTextAt(passwordField, string);
    }

    public void clickNameField() {
        WebElement field = driver.findElement(nameField);
        clickAtElement(field);
    }

    public void clickRegisterButton() {
        WebElement button = driver.findElement(registerButton);
        clickAtElement(button);
    }

    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        clickAtElement(button);
    }

    public WebElement getNameField() {
        return driver.findElement(nameField);
    }

    public WebElement getEmailField() {
        return driver.findElement(emailField);
    }

    public List<WebElement> getValidationErrorMessage() {
        return driver.findElements(validationErrorMessage);
    }

    public WebElement getPasswordField() {
        return driver.findElement(passwordField);
    }

    public WebElement getRegisterButton() {
        return driver.findElement(registerButton);
    }

}
