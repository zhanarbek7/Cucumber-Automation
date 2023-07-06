package steps.db;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.DBUtilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DbConnect_steps {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @Given("user connects to the db")
    public void user_connects_to_the_db() {
        connection = DBUtilities.getConnection();
    }

    @When("user gets all countries data by running {string}")
    public void userGetsAllCountriesDataByRunning(String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("check if there is {string}")
    public void checkIfThereIsCanada(String country) {
        try {
            List<List<String>> queryResultAsListOfList = DBUtilities.getQueryResultAsListOfList(resultSet);
            for (List<String> strings : queryResultAsListOfList) {
                for (int i = 0; i < strings.size(); i++) {
                    if(strings.get(i).equals(country)){
                        System.out.println("Country: " + country + " was found!");
                        System.out.println("Country_ID: "+strings.get(i));
                        System.out.println(strings);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
