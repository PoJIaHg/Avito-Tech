import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class CounterPageTest {
    public static void main(String[] args) {
        // Установка пути к драйверу браузера
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            // Открытие страницы счётчиков
            driver.get("https://www.avito.ru/avito-care/eco-impact");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Получение элементов счётчиков
            WebElement waterCounter = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div/div/div/div/div[3]/div/div[2]/div[4]/div/div[1]"));
            WebElement co2Counter = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div/div/div/div/div[3]/div/div[2]/div[2]/div/div[1]"));
            WebElement energyCounter = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div/div/div/div/div[3]/div/div[2]/div[6]/div/div[1]"));

            // Создание скриншота каждого счётчика
            takeScreenshot(driver, waterCounter, "waterCounter.png");
            takeScreenshot(driver, co2Counter, "co2Counter.png");
            takeScreenshot(driver, energyCounter, "energyCounter.png");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Закрытие браузера
            driver.quit();
        }
    }

    // Метод для создания скриншота элемента
    public static void takeScreenshot(WebDriver driver, WebElement element, String fileName) throws IOException {
        // Преобразование страницы в изображение
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Сохранение скриншота в файл
        FileUtils.copyFile(screenshot, new File("output/" + fileName));
    }
}
