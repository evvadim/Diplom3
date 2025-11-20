import browser.Browser;
import browser.BrowserFactory;
import config.Config;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;

import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class ScrollToAnchorsTest {

    private WebDriver driver;
    private final Browser browser = new BrowserFactory().prepareBrowserNamed(Config.getBrowser());

    private final String anchorName;
    private final int anchorNumber;

    MainPage mainPage;

    public ScrollToAnchorsTest(int anchorNumber, String anchorName) {
        this.anchorName = anchorName;
        this.anchorNumber = anchorNumber;
    }

    @Parameterized.Parameters(name = "Testing Scroll Constructor. Scroll to {1}")
    public static Object[][] getData() {
        return new Object[][] {
                {1, "Булки"},
                {3, "Начинки"},
                {2, "Соусы"},
                {1, "Булки"}
        };
    }

    @Before
    public void setUp() throws InterruptedException {

        browser.driverManagerSetup();
        driver = browser.getNewDriver();

        driver.get(Config.getBaseURI());
        mainPage = new MainPage(driver);

        if (anchorNumber == 1) {
            mainPage.clickAnchorButton("Начинки");
            sleep(1200);
        }

    }

    @Test
    @DisplayName("Test Constructor scroll to Anchor")
    public void scrollToAnchorTests() throws InterruptedException {

        mainPage.clickAnchorButton(anchorName);

        sleep(1200);

        Double scrollTopValue = getScrollTopValue(mainPage.getIngredientsScrollContainer());
        Double hideScrollTopValue = getTotalHeight(mainPage.getIngredientsScrollContainer(), anchorNumber);

        assertThat("Конструктор прокрутился недостаточно", Math.abs(scrollTopValue - (hideScrollTopValue + 40)) < 4);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private Double getScrollTopValue(WebElement element) {

        String value = Objects.requireNonNull(((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", element)).toString();
        return Double.parseDouble(value);

    }

    private Double getHeightValue(WebElement element) {

        String value = Objects.requireNonNull(((JavascriptExecutor) driver).executeScript("return arguments[0].scrollHeight;", element)).toString();
        return Double.parseDouble(value);

    }

    private Double getTotalHeight(WebElement ofSubElements, int n) {

        List<WebElement> elements = ofSubElements.findElements(By.xpath("./*"));
        double total = 0.0;

        for (int i = 0; i < (n - 1); i++) {
            total += getHeightValue(elements.get(2 * i)) + 40 + 24;
            total += getHeightValue(elements.get(2 * i + 1));
        }

        return total;

    }

}
