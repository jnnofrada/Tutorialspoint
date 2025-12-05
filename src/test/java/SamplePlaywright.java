import com.microsoft.playwright.*;
import java.util.regex.Pattern;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SamplePlaywright {
    public static void main(String[] args){
        try (Playwright playwright = Playwright.create()){
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");
            System.out.println("Page Title: " + page.title());
            page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("screenshot.png")));
            browser.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
