package StepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.sl.In;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class MyStepdefs {

    @Given("the user creates a GET request and verifies the response")
    public void the_user_creates_a_GET_request_and_verifies_the_response()throws Throwable {
        baseURI="https://reqres.in/";

        baseURI="https://reqres.in";
        Response response = given().
                when().get("/api/users?page=2");

        JsonPath jsonPath = response.jsonPath();

        response.prettyPrint();
        Integer [] arr={7,8,9,10,11,12};
        List<Integer> listOfIdsExpected=new ArrayList<>(Arrays.asList(arr));

        List<Integer> listofIdsActual= jsonPath.getList("data.id");

        //assert statuscode 200
        Assert.assertEquals(200, response.statusCode());

        //assert data size
        response.then().assertThat().body("data",hasSize(6));

        //assert id values
        Assert.assertEquals(listOfIdsExpected,listofIdsActual);


        //System.out.println(list.size());
        //System.out.println(list);
    }



    @Given("the user creates a POST request and verifies the response")
    public void the_user_creates_a_POST_request_and_verifies_the_response() throws Throwable{

        baseURI="https://reqres.in/";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","cengiz");
        jsonObject.put("job","sdet");

        Response response = given().
                body(jsonObject.toJSONString()).
                when().post("/api/users");

        response.then().assertThat().statusCode(201);
        response.prettyPrint();

    }

}
