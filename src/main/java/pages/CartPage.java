package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void placeOrder(String name, String country, String city, String credit, String month, String year) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Thread.sleep(1000);
        driver.findElement(By.className("btn-success")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("card")).sendKeys(credit);
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).sendKeys(year);
        driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
    }
    public List<Hashtable<String, String>> getProducts() {
        List<Hashtable<String, String>> prodList = new ArrayList<Hashtable<String, String>>();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("success")));
        List<WebElement> products = driver.findElements(By.className("success"));
        for(WebElement element : products) {
            Hashtable<String, String> prod = new Hashtable<String, String>();
            prod.put("title", element.findElements(By.tagName("td")).get(1).getText());
            prod.put("price", element.findElements(By.tagName("td")).get(2).getText());
            prodList.add(prod);
        }
        return prodList;
    }

    public int getTotal() {
        WebElement totalp = driver.findElement(By.id("totalp"));
        return Integer.parseInt(totalp.getText());
    }

    public int getSum() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(3)"));
        int sum = 0;
        for(WebElement element : elements) {
            sum += Integer.parseInt(element.getText());
        }
        return sum;
    }

    public CartPage deleteProduct(int id) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Delete']")));
        List<WebElement> as = driver.findElements(By.xpath("//a[text()='Delete']"));
        as.get(id).click();
        return new CartPage(driver);
    }
}
