package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPassword {

    private final WebDriver driver;

    public ForgotPassword(WebDriver driver) {
        this.driver = driver;
    }

    // локатор кнопки Войти
    private final By loginButton = By.xpath(".//a[@class='Auth_link__1fOlj']");

    private void clickAtElement(WebElement button) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    @Step("Click Login button")
    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        clickAtElement(button);
    }

}
