package Day8;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
public class BookItAuthTest {
    @BeforeClass
    public void beforeClass(){
        baseURI= "https://cybertek-reservation-api-qa2.herokuapp.com";
    }
    @Test
    public void getAllCampuses(){
        String accessToken="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0.3YSCwcTXRcEygBm7LvBLb6_D8jU5WXjAD6E3VA9oh0o";
        Response response = given().header("Authorization ",accessToken).
                when().get("/api/campuses");
        response.prettyPrint();
        System.out.println(response.statusCode());
    }
}
