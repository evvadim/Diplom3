import browser.Browser;
import browser.BrowserFactory;
import config.Config;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

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
    public void setUp() {

        browser.driverManagerSetup();
        driver = browser.getNewDriver();

        driver.get(Config.getBaseURI());
        mainPage = new MainPage(driver);

        if (anchorNumber == 1) {
            mainPage.clickAnchorButton("Начинки");
        }

    }

    @Test
    @DisplayName("Test Constructor scroll to Anchor")
    public void scrollToAnchorTests() {

        mainPage.clickAnchorButton(anchorName);

        Double scrollTopValue = mainPage.getScrollTopValue(mainPage.getIngredientsScrollContainer());
        Double hideScrollTopValue = mainPage.getTotalHeight(mainPage.getIngredientsScrollContainer(), anchorNumber);

        assertThat("Конструктор прокрутился недостаточно", Math.abs(scrollTopValue - (hideScrollTopValue + 40)) < 4);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
