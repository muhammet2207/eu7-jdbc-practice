package Day6_POJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
public class PostRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"MikeEU",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
    @Test
    public void PostNewSpartan(){
        String jsonBody="{\n" +
                "\"gender\":\"Male\",\n" +
                "\"name\":\"MikeSmith\",\n" +
                "\"phone\":12312312312\n" +
                "    \n" +
                "}";
        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/api/spartans");
        //verify status code 201 for post
        assertEquals(response.statusCode(),201);

        //verify content type
        assertEquals(response.contentType(),"application/json");
        //verify successful message
        String actualMessage=response.path("success");
        String expectedMessage="A Spartan is Born!";
        assertEquals(actualMessage,expectedMessage);

        //assertion for spartan data
        String name=response.path("data.name");
        String gender=response.path("data.gender");
        long phone=response.path("data.phone");
        assertEquals(name,"MikeSmith");
    }
    @Test
    public void PostNewSpartan2(){
        //create a map to keep request json body information
        Map<String,Object> requestMAp=new HashMap<>();
        requestMAp.put("name","MikeSmith");
        requestMAp.put("gender","Male");
        requestMAp.put("phone",12312312312l);

        given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMAp)
                .when().post("/api/spartans")
                .then().log().all().statusCode(201)
                .and().contentType("application/json")
                .and().body("success",is("A Spartan is Born!"),
                "data.name",equalTo("MikeSmith"),
                "data.gender",equalTo("Male"),
                "data.phone",equalTo(12312312312l));
    }
    @Test
    public void PostNewSpartan3(){
        Spartan spartanEU=new Spartan();
        spartanEU.setName("MikeSmith");
        spartanEU.setGender("Male");
        spartanEU.setPhone(12312312312l);

        given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanEU)
                .when().post("/api/spartans")
                .then().log().all().statusCode(201)
                .and().contentType("application/json")
                .and().body("success",is("A Spartan is Born!"),
                        "data.name",equalTo("MikeSmith"),
                        "data.gender",equalTo("Male"),
                        "data.phone",equalTo(12312312312l));
    }
}
