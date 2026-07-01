package browser;

public class BrowserFactory {

    public Browser prepareBrowserNamed(String browserName) {

        switch (browserName.toLowerCase()) {
            case "chrome": {
                return new Chrome();
            }
            case "yandex": {
                return new Yandex();
            }
            default:
                throw new IllegalStateException("Unexpected browser name: " + browserName.toLowerCase());
        }

    }

}
