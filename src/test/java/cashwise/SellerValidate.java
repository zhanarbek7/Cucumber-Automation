package cashwise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;
import utilities.Config;

public class SellerValidate {

    @Test
    public void singleSeller() throws JsonProcessingException {
        String path = "/api/myaccount/sellers/94";
        APIRunner.runGET(path);
        Assert.assertEquals("Connection failed", 200, APIRunner.getCustomResponse().getStatusCode());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CustomResponse myResponse = mapper.readValue(APIRunner.getCustomResponse().getJsonString(), CustomResponse.class);

        Assert.assertNotNull("Company name is null", myResponse.getCompany_name());
        Assert.assertNotNull(myResponse.getSeller_name());
        Assert.assertFalse(myResponse.getCompany_name().trim().isEmpty());
        Assert.assertFalse(myResponse.getSeller_name().trim().isEmpty());
    }

    @Test
    public void createSeller(){
        Faker faker = new Faker();
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().cellPhone());
        requestBody.setAddress(faker.address().city());

        String path = "/api/myaccount/sellers";
        APIRunner.runPost(path, requestBody);
        Response response = RestAssured.given().auth().oauth2(Config.getValue("cashwiseToken"))
                .contentType(ContentType.JSON).body(requestBody)
                .post("https://backend.cashwise.us/api/myaccount/sellers");
        System.out.println(response.asPrettyString());

        Assert.assertEquals(200, response.statusCode());


        int id = response.jsonPath().get("seller_id");

        String url = "https://backend.cashwise.us/api/myaccount/sellers/"+id;
        Response response1 = RestAssured.given().auth().oauth2(Config.getValue("cashwiseToken")).get(url);
        Assert.assertEquals(200, response1.statusCode());

        System.out.println("Get one seller");
        System.out.println(response1.asPrettyString());
    }
}
