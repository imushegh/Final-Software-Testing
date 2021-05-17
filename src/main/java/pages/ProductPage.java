package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getName() {
        WebElement h2 = driver.findElement(By.tagName("h2"));
        return h2.getText();
    }

    public String getPrice() {
        WebElement h3 = driver.findElement(By.tagName("h3"));
        return h3.getText();
    }

    public String getDescription() {
        WebElement myTabContent = driver.findElement(By.id("myTabContent"));
        return myTabContent.findElement(By.tagName("p")).getText();
    }

    public String addToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        Thread.sleep(1000);
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }

    public HomePage toHome() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nava"))).click();
        return new HomePage(driver);
    }
}
