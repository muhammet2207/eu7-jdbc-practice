package apitests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
public class hrApiWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }
    @Test
    public void test1(){
        Response response=get("/countries");
        //assign to jsonPath
        JsonPath jsonPath=response.jsonPath();
        String secondCountryName = jsonPath.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);
        //get all country ids
        List<String> countryIds=jsonPath.getList("items.country_id");
        System.out.println(countryIds);

        //get all country names where their region id is equal to 2
        List<String> countryNamesWithRId2=jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println(countryNamesWithRId2);

    }
    @Test
    public void test2(){
        Response response= given().queryParam("limit",107)
                .when().get("/employees");
        JsonPath jsonPath=response.jsonPath();
        //get me all first name of employees who is working as IT_PROG
        List<String> firstNameOfItProg=jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.first_name");
        System.out.println(firstNameOfItProg);
        //get all firstnames who makees more than 10000
        List<String> richFirstNames=jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println(richFirstNames);
        //get me firstname who makes max salary
        String maxSalaryFirstName=jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println(maxSalaryFirstName);
    }
}
