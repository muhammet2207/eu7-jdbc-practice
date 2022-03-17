package apitests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
public class CBTrainingWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("cbt_api_url");
    }
    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",24668)
                .when().get("/student/{id}");
        //verify students code
        assertEquals(response.statusCode(),200);

        //assign response to jsonPath
        JsonPath jsonPath=response.jsonPath();
        String firstName= jsonPath.getString("students.firstName[0]");
        System.out.println(firstName);

        //bring the phone number
        String phoneNumber= jsonPath.getString("students.contact[0].phone");
        System.out.println(phoneNumber);

        //get me city
        String city= jsonPath.getString("students.company[0].address.city");
        assertEquals(city,"string");

        String firstName2=jsonPath.getString("students.firstName");
        System.out.println(firstName2);
    }
}
