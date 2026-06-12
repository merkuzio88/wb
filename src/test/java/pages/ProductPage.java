package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By addToCartBtn = By.xpath("//div[contains(@class, 'actionsBlockMain')]//button[@aria-label='Добавить в корзину']");
    private final By inCartBtn = By.xpath("//div[contains(@class, 'actionsBlockMain')]//button[@aria-label='В корзине']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открываем страницу товара по ссылке: {url}")
    public void open(String url) {
        driver.get(url);
    }

    @Step("Нажимаем на кнопку 'Добавить в корзину'")
    public void clickAddToCart() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        button.click();
    }

    @Step("Нажимаем на появившуюся кнопку 'В корзине' для перехода")
    public CartPage clickGoToCart() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(inCartBtn));
        button.click();

        return new CartPage(driver);
    }
}
