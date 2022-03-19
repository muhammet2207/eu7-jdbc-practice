package Day8;
import org.testng.annotations.*;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.*;

public class SpartanFlow {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    @Test
    public void PostNewSpartan(){

    }
    @Test
    public void PutExistingSpartan(){

    }
    @Test
    public void PatchExistingSpartan(){

    }
    @Test
    public void GetThatSpartan(){

    }
    @Test
    public void DeleteThatSpartan(){

        given().
                pathParam("id",89)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204).log().all();
    }
}
