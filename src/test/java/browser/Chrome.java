package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome extends Browser {

    @Override
    public void driverManagerSetup() {
        WebDriverManager.chromedriver();
    }

    @Override
    public WebDriver getNewDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        options.addArguments("--window-size=1600,900");
        return new ChromeDriver(options);
    }
}
