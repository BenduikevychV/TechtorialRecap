package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

public class Driver {

    private static WebDriver driver;

    public static WebDriver getDriver() {

//        String browser = "chrome";
        String browser = null;
        try {
            browser = Config.getProperty("uiconfigs","browser");
        }catch (IOException e){
            e.printStackTrace();
        }

        if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
            if (browser.equals("chrome")) {

                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                return driver;
            } else if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                return driver;
            } else if (browser.equals("ie")) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                return driver;
            }
        }
        return driver;
    }

    public static void closer() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
