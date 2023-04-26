package steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

public class GetAllClients_steps {
    @Given("user hits get all cleints API {string} {string} {string} {string}")
    public void userHitsGetAllCleintsAPI(String path, String isArchived, String page, String size) {
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", isArchived);
        params.put("page", page);
        params.put("size", params);

    }

    @Then("user verfies that total number of clients should be {string}")
    public void userVerfiesThatTotalNumberOfClientsShouldBe(String totalExpected) {

    }
}
