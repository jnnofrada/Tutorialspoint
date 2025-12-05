import com.microsoft.playwright.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ScreenshotVideoExample {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get("videos"));
            BrowserContext context = browser.newContext(contextOptions);
            Page page = context.newPage();
            page.navigate("https://playwright.dev/");
            Locator codeGen = page.getByText("Codegen");
            codeGen.scrollIntoViewIfNeeded();
            assertThat(codeGen).hasText("Codegen");
            codeGen.click();

            // Take screenshot
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshot.png")));

            // Close context (video will be saved in "videos" folder)
            context.close();
            browser.close();
        }
    }
}