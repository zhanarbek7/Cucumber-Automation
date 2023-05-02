package cashwise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.Config;

public class Login {
    @Test
    public void authorization(){
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail("test@tester.com");
        requestBody.setPassword("123456");

        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody)
                .post(Config.getValue("cashwiseBackend") + "/api/myaccount/auth/login");

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            System.out.println(customResponse.getJwt_token());
            System.out.println(customResponse.getMessage());
        } catch (JsonProcessingException e) {
            System.out.println("Couldn't parse JSON response to object");
        }
    }

    // Categories income is fully empty, so this one will not work will throw nullPoint
    @Test
    public void getIncomeCatergories(){
        String token = Config.getValue("cashwiseToken");
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .auth().oauth2(token).get(Config.getValue("cashwiseBackend")+"/api/myaccount/categories/income");

        System.out.println("Status code: "+ response.statusCode());
        for(int i = 0; i < 61; i++){
            String path = "[" + i + "].flag";
            boolean flag = response.jsonPath().get(path);
            Assert.assertTrue(flag);
            String path2 = "[" + i + "].category_title";
            String title = response.jsonPath().get(path2);
            if(title.trim().isEmpty()){
                System.out.println("Found at: "+ i);
            }
        }
    }
}
