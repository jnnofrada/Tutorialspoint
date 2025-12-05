import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParallelTests {

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
    @Order(1)
    void testGoogle() {
        try (Playwright playwright = Playwright.create()) {
            page.navigate("https://www.google.com");
            System.out.println("Title: " + page.title());
        }
    }

    @Test
    @Order(2)
    void testBing() {
        try (Playwright playwright = Playwright.create()) {
            page.navigate("https://www.bing.com");
            System.out.println("Title: " + page.title());
        }
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