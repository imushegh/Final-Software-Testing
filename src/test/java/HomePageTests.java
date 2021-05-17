import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class HomePageTests extends BaseTests {
    @Test
    public void testContact() {
        String text = homePage.contact();
        assertEquals(text, "Thanks for the message!!");
    }

    @Test
    public void testChangeCategory() throws InterruptedException {
        List<String> list1 = Arrays.asList("HTC One M9", "Iphone 6 32gb", "Nexus 6", "Nokia lumia 1520", "Sony xperia z5", "Samsung galaxy s7", "Samsung galaxy s6");
        List<String> list2 = Arrays.asList("Sony vaio i5", "Sony vaio i7", "MacBook air", "Dell i7 8gb", "2017 Dell 15.6 Inch", "MacBook Pro");
        List<String> list3 = Arrays.asList("Apple monitor 24", "ASUS Full HD");
        homePage.changeCategory("Phones");
        Thread.sleep(1000);
        assertEquals(new HashSet<String>(homePage.getItems()), new HashSet<String>(list1));
        homePage.changeCategory("Laptops");
        Thread.sleep(1000);
        assertEquals(new HashSet<String>(homePage.getItems()), new HashSet<String>(list2));
        homePage.changeCategory("Monitors");
        Thread.sleep(1000);
        assertEquals(new HashSet<String>(homePage.getItems()), new HashSet<String>(list3));
    }

    @Test
    public void testSignUp() throws InterruptedException {
        String[] strArr = { "P", "Q", "R", "S","T", "U", "V", "W", "a", "1" };
        Random rand = new Random();
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            username.append(strArr[rand.nextInt(strArr.length)]);
        }
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            password.append(strArr[rand.nextInt(strArr.length)]);
        }
        String message = homePage.signUp(username.toString(), password.toString());
        assertEquals(message, "Sign up successful.");
    }

    @Test
    public void testSignIn() throws InterruptedException {
        homePage.signIn("mushegh", "password1");
        Thread.sleep(2000);
        assertEquals(homePage.getUsername(), "Welcome mushegh");
    }


    @Test
    public void testLogOut() throws InterruptedException {
        homePage.signIn("mushegh", "password1");
        Thread.sleep(2000);
        homePage.logOut();
        assertEquals(homePage.getUsername(), "");
    }
}
