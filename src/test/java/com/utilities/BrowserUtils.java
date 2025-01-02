package com.utilities;


import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;


public class BrowserUtils {



    /**
     * This method accepts a String "expectedInURL" and changes the window depends on expectedInURL
     * @param expectedInURL
     */
    public static void switchToWindow( String expectedInURL){
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();
        for (String each : allWindowHandles) {
            Driver.getDriver().switchTo().window(each);
            if (Driver.getDriver().getCurrentUrl().equals(expectedInURL)){
                break;
            }
        }
    }

    /**
      This method accepts a String "expectedTitle" and Asserts if it is true
     */
    public static void verifyTitle( String expectedTitle){
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals("Başlık uyuşmuyor!",expectedTitle,actualTitle);

    }

    /**
     * Moves the mouse to given element
     * @param element on which to hover
     */
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * Waits for the provided element to be visible on the page
     *
     * @param element
     * @param timeToWaitInSec
     */
   /* public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
*/
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        // WebDriverWait'i Selenium 4'ün yeni kullanımı ile güncelliyoruz
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));

        // ExpectedConditions ile elementin görünür olmasını bekliyoruz
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Waits until the given number of windows has been occurred
     *
     */
    public static void waitNumberOfWindowsToBe(int numberOfWindows) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
    }

    /**
     * Highlighs an element by changing its background and border color
     * @param element
     */
    public static void highlight(WebElement element){
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    /**
     * Waits for provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return
     */
    public static WebElement waitForClickAbility(WebElement element, int timeout) {
        // WebDriverWait için bekleme süresi Duration ile ayarlanır
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));

        // Elementin tıklanabilir olmasını bekleriz
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }




}
