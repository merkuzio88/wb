import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CartPage;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Управление корзиной")
@Feature("Добавление товара")
public class WildberriesCartTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--window-size=1920,1080");

        boolean isGitHubActions = Boolean.parseBoolean(System.getenv("GITHUB_ACTIONS"));
        boolean isLocalHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (isGitHubActions || isLocalHeadless) {
            options.addArguments("--headless=new");
        } else {
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);
    }

    @Test
    @DisplayName("Проверка добавления товара в корзину")
    @Description("Тест проверяет сценарий добавления конкретного товара (книги) в корзину со страницы товара и его наличие в самой корзине.")
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
