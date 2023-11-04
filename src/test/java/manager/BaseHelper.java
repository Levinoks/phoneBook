package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BaseHelper {

    Logger logger = LoggerFactory.getLogger(BaseHelper.class);

    WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement findElementBase(By locator) {
        logger.info("search element by locator: " + locator);
        System.out.println(locator);
        return driver.findElement(locator);
    }

    private List<WebElement> findElementsBase(By locator) {
        System.out.println(locator);
        return driver.findElements(locator);
    }

    public void clickBase(By locator) {
        WebElement el = findElementBase(locator);
        el.click();
    }

    public String getTextBase(By locator) {
        WebElement el = findElementBase(locator);
        return el.getText().trim().toUpperCase();
    }

    public void typeTextBase(By locator, String text) {
        WebElement el = findElementBase(locator);
        el.click();
        el.clear();
        el.sendKeys(text);
    }

    public boolean isTextEqual(By locator, String expectedResult) {
        String actualResult = getTextBase(locator);
        expectedResult = expectedResult.toUpperCase();
        return isTextContainsGet2Strings(expectedResult, actualResult);
    }

    public boolean isTextContainsGet2Strings(String expectedResult, String actualResult) {
        if (actualResult.contains(expectedResult)) {
            return true;
        } else {
            System.out.println("expected result: " + expectedResult +
                    "actual result: " + actualResult);
            return false;
        }
    }

    public String getTextAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText().toUpperCase().trim();
    }

    public void clickAcceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();


    }

    public void jsClickBase(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(locator);
    }

    public void clickByXY( int x, int y){

        Actions act = new Actions(driver);
        act.moveByOffset(x,y).click().build().perform();
    }


    public void refresh() {
        driver.navigate().refresh();
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementByTextExistInList(By locator, String text) {
        boolean flag = false;
        List<WebElement> list = findElementsBase(locator);
        int size = list.size();
        if (size == 0) {
            return false;
        }
        for (WebElement e:list) {
            if (e.getText().equals(text)) {
                flag = true;
                break;
            }
        }
        return flag;
    }



}
