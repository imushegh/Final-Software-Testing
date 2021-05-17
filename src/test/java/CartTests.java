import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import static org.testng.Assert.assertEquals;

public class CartTests extends BaseTests {

    @Test
    public void testPlaceOrder() throws InterruptedException {
        CartPage cart = homePage.getCart();
        cart.placeOrder("Armen", "Armenia", "Yerevan", "2343298572389", "12", "1997");
    }

    @Test
    public void testGetSum() throws InterruptedException {
        ProductPage samsung = homePage.selectItem("Samsung galaxy s6");
        samsung.addToCart();
        HomePage homePage = samsung.toHome();
        homePage.selectItem("Nokia lumia 1520").addToCart();
        CartPage cart = homePage.getCart();
        assertEquals(cart.getSum(), cart.getTotal());
    }

    @Test
    public void testDeleteProduct() throws InterruptedException {
        ProductPage samsung = homePage.selectItem("Samsung galaxy s6");
        samsung.addToCart();
        HomePage homePage = samsung.toHome();
        homePage.selectItem("Nokia lumia 1520").addToCart();
        CartPage cart = homePage.getCart();
        cart.deleteProduct(1);
        assertEquals(cart.getSum(), cart.getTotal());
    }
}
