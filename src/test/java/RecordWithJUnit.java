import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecordWithJUnit{
    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    Page page;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos"));
        context = browser.newContext(contextOptions);
    }

    @BeforeEach
    void createPage() {
        page = context.newPage();
    }

    @Test
    void testExampleTitle() {
        page.navigate("https://playwright.dev/");
        Locator codeGen = page.getByText("Codegen");
        codeGen.scrollIntoViewIfNeeded();
        assertThat(codeGen).hasText("Codegen.");
        codeGen.click();

        // Take screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshot.png")));
    }

    @Test
    void testExampleAPI() {
        page.navigate("https://playwright.dev/");
        Locator apiGen = page.locator("xpath=//a").filter(new Locator.FilterOptions().setHasText("API"));
        apiGen.scrollIntoViewIfNeeded();
        assertEquals("Attribute not a match!", "navbar__item navbar__link", apiGen.getAttribute("class"));
        apiGen.click();

        // Take screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshot.png")));
    }

    @AfterEach
    void closePage() {
        page.close();
    }

    @AfterAll
    static void tearDown() {
//        browser.close();
        context.close();
        playwright.close();
    }
}
