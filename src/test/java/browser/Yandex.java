package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Yandex extends Browser {

    @Override
    public void driverManagerSetup() {
        WebDriverManager.chromedriver().driverVersion("140.0.7339.207").setup();
    }

    @Override
    public WebDriver getNewDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        options.addArguments("--window-size=1600,900");
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        return new ChromeDriver(options);
    }
}
