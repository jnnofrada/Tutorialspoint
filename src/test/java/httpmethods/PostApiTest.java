package httpmethods;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostApiTest {

    @Test
    public static void PostApiTest() {

        // Defining the request body.
        String requestBody = """
                        {
                            "name": "Rushabh Shah",
                            "job": "QA Automation Engineer"
                        }
                        """;

        //Converting request body to JSON.
        JsonPath expectedResponse = new JsonPath(requestBody);

        // Performing POST request and validating its response.
        given()
                // Specifying the Base URI.
                .baseUri("https://reqres.in/api")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                //HTTP Request Method.
                .post("/users")
                .then()
                //Ensuring that status code is 201 OK.
                .statusCode(201)
                //Validating Name value.
                .body("name", equalTo(expectedResponse.getString("name")))
                //Validating Job value.
                .body("job", equalTo(expectedResponse.getString("job")))
                // Ensuring that ID will be generated and returned in response is not null.
                .body("id", notNullValue())
                // Log response details
                .log().all();
    }
}