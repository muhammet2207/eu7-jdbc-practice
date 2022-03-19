package Day8;
import groovy.lang.DelegatesTo;
import io.restassured.http.ContentType;
import org.testng.annotations.*;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
public class PutRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    @Test
    public void Puttest1(){
        //Create one map for the put request json body
        Map<String,Object> putRequestMap=new HashMap<>();
        putRequestMap.put("name","PutName");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone","123123123123");

        given().log().all()
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",89)
                .and().body(putRequestMap)
                .when()
                .put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);
    //send get request to verify body
    }
    @Test
    public void PatchTest(){
        //Create one map for the request json body
        Map<String,Object> patchRequestMap=new HashMap<>();
        patchRequestMap.put("name","TJ");


        given().log().all()
                .and().contentType(ContentType.JSON)
                .and().pathParam("id",89)
                .and().body(patchRequestMap)
                .when()
                .patch("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);
    }
}
