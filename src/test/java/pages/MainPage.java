package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private final WebDriver driver;

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    private final By accountButton = By.xpath(".//a[@class='AppHeader_header__link__3D_hX']/p[text()='Личный Кабинет']/parent::a");

    private final By ingredientsScrollContainer = By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo");

    private By anchorButtonNamed(String string) {
        return By.xpath(String.format(".//span[text()='%s']/parent::div", string));
    }

    private By anchorHeaderNamed(String string) {
        return By.xpath(String.format(".//h2[text()='%s']", string));
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // вспомагательный метод заполнения текстовых полей
    private void fillInputTextAt(By locator, String inputText) {
        WebElement input = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", input);
        input.sendKeys(inputText);
    }

    private void clickAtElement(WebElement button) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    public void clickLoginButton() {
        WebElement button = driver.findElement(loginButton);
        clickAtElement(button);
    }

    public void clickAccountButton() {
        WebElement button = driver.findElement(accountButton);
        clickAtElement(button);
    }

    public void clickAnchorButton(String string) {
        WebElement button = driver.findElement(anchorButtonNamed(string));
        clickAtElement(button);
    }

    public WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }

    public WebElement getAccountButton() {
        return driver.findElement(accountButton);
    }

    public WebElement getIngredientsScrollContainer() {
        return driver.findElement(ingredientsScrollContainer);
    }

    public WebElement getAnchorButtonNamed(String string) {
        return driver.findElement(anchorButtonNamed(string));
    }

    public WebElement getAnchorHeaderNamed(String string) {
        return driver.findElement(anchorHeaderNamed(string));
    }

}
