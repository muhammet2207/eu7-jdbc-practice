package Day8;
import org.testng.annotations.*;
import utilities.ConfigurationReader;

import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
public class DeleteRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    @Test
    public void test1(){
        Random rd=new Random();
        int idToDelete=rd.nextInt(200)+1;
        given().
                pathParam("id",idToDelete)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204).log().all();
    }
    
}
