package steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class GetAllClients_steps {
    @Given("user hits get all cleints API {string} {string} {string} {string}")
    public void userHitsGetAllCleintsAPI(String path, String isArchived, String page, String size) {
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", isArchived);
        params.put("page", page);
        params.put("size", size);
        APIRunner.runGET(path, params);
    }

    @Then("user verifies that total number of clients should be {string}")
    public void userVerfiesThatTotalNumberOfClientsShouldBe(String totalExpected) {
        int numberOfClients = APIRunner.getCustomResponse().getResponses().size();
        Assert.assertEquals(totalExpected, String.valueOf(numberOfClients));
    }
}
