package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class simpleGetRequest {
    String hrurl="http://44.202.61.16:1000/ords/hr/regions";
    @Test
    public void test1(){

        Response response = RestAssured.get(hrurl);
        //print the status code
        System.out.println(response.statusCode());
        //Print the json body
        response.prettyPrint();
    }
    /*
    Given accept type is json
    When user sends get request to regions endpoint
    Then response status code ust be 200
    and body is json format
    */

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl);
        Assert.assertEquals(response.statusCode(),200);
        System.out.println(response.contentType());
        Assert.assertEquals(response.contentType(),"application/json");
    }
    @Test
    public void test3(){
        RestAssured.given().accept(ContentType.JSON)
                   .when().get(hrurl).then()
                   .assertThat().statusCode(200)
                   .and().contentType("application/json");
    }
     /*
    Given accept type is json
    When user sends get request to regions/2
    Then response status code ust be 200
    and body is json format
    and response body contains Americas
    */
  @Test
  public void test4(){
      Response response = given().accept(ContentType.JSON)
              .when().get(hrurl + "/2");

              Assert.assertEquals(response.contentType(),"application/json");

            Assert.assertTrue(response.body().asString().contains("Americas"));
  }
}
