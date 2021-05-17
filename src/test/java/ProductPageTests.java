import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

import static org.testng.Assert.assertEquals;

public class ProductPageTests extends BaseTests {
    @Test
    public void testAddToCart() throws InterruptedException {
        ProductPage samsung = homePage.selectItem("Samsung galaxy s6");
        samsung.addToCart();
        HomePage homePage = samsung.toHome();
        homePage.selectItem("Nokia lumia 1520").addToCart();
        assertEquals(homePage.getCart().getProducts().get(0).get("title"), "Samsung galaxy s6");
        assertEquals(homePage.getCart().getProducts().get(1).get("title"), "Nokia lumia 1520");
    }
}
