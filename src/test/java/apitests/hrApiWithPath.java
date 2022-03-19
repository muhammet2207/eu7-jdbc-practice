package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;


public class hrApiWithPath {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }
    @Test
    public void getCountriesWithPath(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .when().get("/countries");
        assertEquals(response.statusCode(),200);
        //print limit value
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        String firstCountryId=response.path("items.country_name[1]");
        System.out.println("firstCountryId = " + firstCountryId);

        String link1=response.path("items.links[1].href[0]");
        System.out.println("link1 = " + link1);

        //assert that all region_id's are equal to 2
        List<Integer> region_ids=response.path("items.region_id");
        for (int s : region_ids) {
            assertEquals(s, 2);
        }
    }

    //make sure we have only IT_PROG as a job_id

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");
        List<String> job_ids=response.path("items.job_id");
        for (String s : job_ids) {
            assertEquals(s, "IT_PROG");
        }
    }

}
