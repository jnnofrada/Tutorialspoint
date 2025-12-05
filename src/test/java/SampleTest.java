import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SampleTest {

    WebDriver driver;
    String baseUrl = "https://jignect.tech/";

    @BeforeMethod
    public void setUpBrowser() {

        // Initialize ChromeDriver.
        driver = new ChromeDriver();

        // Maximize the browser window size.
        driver.manage().window().maximize();

        // Navigate to the website.
        driver.get(baseUrl);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sampleTest() {

        // Ensure that the user is redirected to the homepage of the JigNect Website.
        WebElement textOnHomePage = driver.findElement(By.xpath("//h1[contains(text(),'Perfecting Your Software ')]"));
        Assert.assertTrue(textOnHomePage.isDisplayed(), "The user hasn't arrived at the JigNect website's main page.");

        // Navigate to the QA Blogs page by clicking on it from the website's header section.
        WebElement qaBlogsHeaderText = driver.findElement(By.xpath("//div[@id='site-header']//a[contains(text(),'QA Blogs')]"));
        qaBlogsHeaderText.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Confirm that the user is currently on the QA Blogs page.
        WebElement headerTextFromQaBlogsPage = driver.findElement(By.xpath("//a[contains(text(),'Test Automation')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", headerTextFromQaBlogsPage);

        Assert.assertTrue(headerTextFromQaBlogsPage.isDisplayed(), "The user hasn't arrived at the QA Blogs page.");
    }

    @AfterMethod
    public void closeBrowser() {

        // Terminate the browser.
        driver.quit();
    }
}