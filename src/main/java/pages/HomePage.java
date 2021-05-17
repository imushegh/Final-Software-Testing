package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String contact() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contact"))).click();
        WebElement contactModal = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("exampleModal"))));
        driver.findElement(By.id("recipient-email")).sendKeys("test@gmail.com");
        driver.findElement(By.id("recipient-name")).sendKeys("Testname");
        driver.findElement(By.id("message-text")).sendKeys("Welcome to the party, pal!");
        driver.findElement(By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]")).click();
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }

    public CartPage getCart() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Cart"))).click();
        return new CartPage(driver);
    }

    public void changeCategory(String category) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        if (category.equals("Phones")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div/div[1]/div/a[2]"))).click();
        } else if (category.equals("Laptops")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div/div[1]/div/a[3]"))).click();
        } else if (category.equals("Monitors")){
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div/div[1]/div/a[4]"))).click();
        }
    }

    public ProductPage selectItem(String name) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(name))).click();
        return new ProductPage(driver);
    }

    public List<String> getItems() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("hrefch")));
        List<WebElement> items = driver.findElements(By.className("hrefch"));
        List<String> itemNames = new ArrayList<String>();
        for(WebElement item : items) {
            itemNames.add(item.getText());
        }
        return itemNames;
    }

    public String signUp(String name, String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 18);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("signin2"))).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("signInModal")));
        driver.findElement(By.id("sign-username")).sendKeys(name);
        driver.findElement(By.id("sign-password")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }

    public void signIn(String name, String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 18);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("logInModal"))));
        driver.findElement(By.id("loginusername")).sendKeys(name);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);
    }

    public String getUsername() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nameofuser"))).getText();
        } catch (Exception e) {
            return "";
        }

    }

    public void logOut() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logout2"))).click();
    }
}