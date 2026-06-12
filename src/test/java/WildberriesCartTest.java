import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CartPage;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WildberriesCartTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testAddProductToCart() {
        String productUrl = "https://www.wildberries.by/catalog/264220770/detail.aspx";
        String expectedProductName = "Щелкунчик по балету Чайковского. Книги для детей";

        ProductPage productPage = new ProductPage(driver);
        productPage.open(productUrl);

        productPage.clickAddToCart();

        CartPage cartPage = productPage.clickGoToCart();

        assertTrue(cartPage.isCartPageOpened(), "Страница корзины не была открыта!");

        boolean isItemFound = cartPage.isProductInCart(expectedProductName);
        assertTrue(isItemFound, "Ожидаемый товар '" + expectedProductName + "' не найден в корзине!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
