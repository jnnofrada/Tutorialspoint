import com.microsoft.playwright.*;

public class NetworkMocking {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Intercept API call and return a mock response
            page.route("**/api/data", route ->
                    route.fulfill(new Route.FulfillOptions().setBody("{\"message\":\"Hello Mocked API!\"}"))
            );

            page.navigate("https://example.com");
            System.out.println("API mocked successfully!");

            browser.close();
        }
    }
}