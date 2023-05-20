package steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.APIRunner;
import utilities.TempStorage;

public class GetUser_steps {
    @Given("user hits gets single user api {string}")
    public void user_hits_gets_single_user_api(String path) {
        // Write code here that turns the phrase above into concrete actions
        Response response = RestAssured.given().get("https://reqres.in/api/users/2");
        System.out.println(response.getStatusCode());
        System.out.println(response.body().prettyPrint());
    }
}
