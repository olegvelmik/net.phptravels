package com.automationpractice.pages;

import com.automationpractice.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;

    protected final Duration timeoutCommon = Duration.ofSeconds(10);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForClickable(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void actionClickElement(String logAction, By locator, Duration timeout) {
        CustomReporter.logAction(logAction);
        waitForClickable(locator, timeout);
        driver.findElement(locator).click();
    }

    public void actionFillField(String logAction, By locator, String inputValue, Duration timeout) {
        CustomReporter.logAction(logAction);
        waitForClickable(locator, timeout);
        driver.findElement(locator).sendKeys(inputValue);
    }

    public boolean elementIsPresent(String logAction, By locator) {
        CustomReporter.log(logAction);
        return !driver.findElements(locator).isEmpty();
    }
}
