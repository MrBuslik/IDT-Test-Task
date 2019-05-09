package framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Compares OS (Linux or Windows) and choose driver (current chrome)
 *
 * @author Yaroslav Lazakovich
 * @version 1.0
 */
public class BrowserFactory extends BaseEntity {
    private static final String OPERATION_SYSTEM_NAME = System.getProperty("os.name");
    private static final String PROPERTY_CHROME = "webdriver.chrome.driver";
    private static final String DRIVER_CHROME = "drivers/chromedriver";
    private static final String CHROME = "chrome";
    private static final String LINUX = "linux";
    private static final String BROWSER_NAME = PropertyReader.getProperty("browserName");
    private static final String URL = PropertyReader.getProperty("url");
    private static String driverPath = "src/main/resources/";
    private static BrowserFactory instance;
    //TODO Log field

    public static BrowserFactory getInstance() {
        try {
            if (instance == null) {
                instance = new BrowserFactory();
            }
        } catch (IOException e) {
            //TODO log.error(io)
        }
        return instance;
    }

    public static String getDriverPath() {
        return driverPath;
    }

    private BrowserFactory() throws IOException {
        driverPath = new File(driverPath).getCanonicalPath();
        //TODO initBrowser()
        //TODO log.info
        //TODO WebDriverManager.openUrl()
    }

    private static String initOs(String operationSysName) {
        System.out.println("Current OS: " + System.getProperties().getProperty("os.name"));
        ;
        return operationSysName.toLowerCase().equals(LINUX) ? "" : ".exe";
    }

    private void setPropertyBrowser(String prop, String driverName) {
        System.setProperty(prop, Paths.get(driverPath, driverName.concat(initOs(OPERATION_SYSTEM_NAME))).toString());
    }

    private void initBrowser(String browserName) {
        System.out.println("Current Browser: " + browserName);
        switch (browserName.toLowerCase()) {
            case CHROME:
                setPropertyBrowser(PROPERTY_CHROME, DRIVER_CHROME);
                driver = new ChromeDriver();
                break;
            default:
                setPropertyBrowser(PROPERTY_CHROME, DRIVER_CHROME);
                driver = new ChromeDriver();
        }
    }

    //TODO check work status for initBrowser();
    @Test
    public void openBrowser() {
        initBrowser(BROWSER_NAME);
    }

}
