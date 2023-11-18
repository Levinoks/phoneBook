package manager;

import datasetup.FillData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigProperties;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    static String browser;

    EventFiringWebDriver driver;
    UserHelper userHelper;
    ContactHelper contactHelper;
    FillData data;

    public ApplicationManager() {
        browser = System.getProperty("browser", BrowserType.CHROME);
    }

    public void init() {
        // driver = new EventFiringWebDriver(new ChromeDriver());

        if (browser.equals(BrowserType.CHROME)) {
            driver = new EventFiringWebDriver(new ChromeDriver());
            logger.info("created chrome browser");
        } else if (browser.equals(BrowserType.FIREFOX)) {
            driver = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("started tests in firefox browser");
        }
        driver.navigate().to(ConfigProperties.getProperty("url"));
        logger.info("open page: " + ConfigProperties.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.register(new WDListener());
        userHelper = new UserHelper(driver);
        contactHelper = new ContactHelper(driver);
        data = new FillData();
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public FillData getData() {
        return data;
    }

    public void tearDown() {
        driver.quit();
    }

    public void navigateToMainPage() {
        driver.navigate().to(ConfigProperties.getProperty("url"));
    }

    public boolean isPageUrlHome() {
        String url = driver.getCurrentUrl();
        System.out.println(url + "-------------------- url");
        return url.equals(ConfigProperties.getProperty("url"));
    }

}
