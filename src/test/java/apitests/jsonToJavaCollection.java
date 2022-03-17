package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class jsonToJavaCollection {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    @Test
    public void spartanToMap(){

        Response response = given().contentType(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        //we will convert json response to java map
        Map<String,Object> jsonDataMap=response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);

        String name=(String) jsonDataMap.get("name");
        assertEquals(name,"Meta");
    }
    @Test
    public void AllSpartansListOfMap(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        assertEquals(response.statusCode(),200);
        //we need to de-serialize JSON respponse to List of Maps

        List<Map<String,Object>> allSpartanList=response.body().as(List.class);
        System.out.println(allSpartanList);
        //print second spartan first name
        System.out.println(allSpartanList.get(1));
        System.out.println(allSpartanList.get(1).get("name"));
        //save spartan 3 in a map
        Map<String,Object> spartan3=allSpartanList.get(2);
        System.out.println("Spartan3-->"+spartan3);
    }
    @Test
    public void regionToMap(){
        Response response=when().get("http://44.202.61.16:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);
        //we de serialize Json response to Map
        Map<String ,Object> regionMap=response.body().as(Map.class);
        System.out.println(regionMap.get("count"));
        System.out.println(regionMap.get("items"));

        List<Map<String,Object>> itesmsList= (List<Map<String, Object>>) regionMap.get("items");
        //print first region name
        System.out.println(itesmsList.get(0).get("region_name"));
    }
}
