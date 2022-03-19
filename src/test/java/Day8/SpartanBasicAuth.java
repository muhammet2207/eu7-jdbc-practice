package Day8;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.*;

public class SpartanBasicAuth {
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("http://44.202.61.16:8000/api/spartans")
                .then().log().all().statusCode(200);



    }
}
