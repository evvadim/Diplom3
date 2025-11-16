package browser;

public class BrowserFactory {

    public Browser prepareBrowserNamed(AvailableBrowsers browserName) {

        switch (browserName.toString().toLowerCase()) {
            case "chrome": {
                return new Chrome();
            }
            case "yandex": {
                return new Yandex();
            }
            default:
                throw new IllegalStateException("Unexpected browser name: " + browserName.toString().toLowerCase());
        }

    }

}
