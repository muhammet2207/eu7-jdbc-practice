package apitests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class SpartanTestWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
         /*
          Given accept type is json
          And path param spartan id is 11
          When user sends a get request to /spartans/{id}
         Then status code is 200
         And content type is Json
         And   "id": 11,
               "name": "Nona",
              "gender": "Female",
              "phone": 7959094216
    */
    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",11)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //verify id and name with path()

        int id=response.path("id");
        String name=response.path("name");

        assertEquals(id,11);
        assertEquals(name,"Nona");

        //assign response to json path
        JsonPath jsonPath=response.jsonPath();

        int idJson=jsonPath.get("id");
        String nameJson=jsonPath.get("name");

        System.out.println("nameJson = " + nameJson);

        assertEquals(nameJson,"Nona");
    }
}
