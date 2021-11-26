package ru.netology.card;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import static org.junit.jupiter.api.Assertions.*;

    public class CardTest {
        private WebDriver driver;

        @BeforeAll
        static void setUpAll() {
            WebDriverManager.chromedriver().setup();
        }

        @BeforeEach
        void setUp() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }

        @AfterEach
        void tearDown() {
            driver.quit();
            driver = null;
        }

        @Test
        void sendCorrectTest() {
            driver.get("http://localhost:9999");
            driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Дарья Лукьянова");
            driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79999999999");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            driver.findElement(By.className("button__text")).click();
            String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
            assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
            }
}
