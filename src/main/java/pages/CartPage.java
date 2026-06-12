package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartItemsNames = By.xpath("//span[contains(@class, 'good-info__good-name')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCartPageOpened() {
        return wait.until(ExpectedConditions.urlContains("basket"));
    }

    public boolean isProductInCart(String expectedProductName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemsNames));

        List<WebElement> items = driver.findElements(cartItemsNames);

        for (WebElement item : items) {
            if (item.getText().contains(expectedProductName)) {
                return true;
            }
        }
        return false;
    }
}
