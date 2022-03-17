package apitests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.regex.Matcher;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
public class HamcrestMatchersApiTest {
    /*
 given accept type is Json
 And path param id is 15
 When user sends a get request to spartans/{id}
 Then status code is 200
 And content type is Json
 And json data has following
     "id": 15,
     "name": "Meta",
     "gender": "Female",
     "phone": 1938695106
  */
    @Test
    public void OneSpartanWithHamcrest(){
       given().accept(ContentType.JSON)
               .and().pathParam("id",15)
               .when().get("http://44.202.61.16:8000/api/spartans/{id}")
               .then().statusCode(200)
               .and().assertThat().contentType("application/json")
               .and().assertThat().body("id",equalTo(15),
               "name",equalTo("Meta"),
                       "gender",equalTo("Female"),
                       "phone",equalTo(1938695106));
    }
    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",10777)
                .when().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().header("Content-Length",equalTo("281"))
                .and().header("Date",notNullValue())
                .and().assertThat().body("teachers.firstName[0]",equalTo("Michale"),
                                "teachers.lastName[0]",equalTo("Tremblay"),
                                        "teachers.gender[0]",equalTo("Male"))
                                .log().all();

    }
    @Test
    public void teacherWithDepartments(){
        given().accept(ContentType.JSON)
                .and().pathParam("name","Computer")
                .when().log().all().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200).and()
                .contentType(equalTo("application/json;charset=UTF-8")).and()
                .body("teachers.firstName",hasItems("Alexander","Marteen"));
    }
}
