package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class APIRunner {

    private static CustomResponse customResponse;

    private static CustomResponse[] responseList;

    public static void runGET(String path){
        String token = Config.getValue("cashwiseToken");
        String url = Config.getValue("cashwiseBackend")+ path;
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("Status code: "+response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        try{
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            customResponse.setJsonString(response.asString());
            customResponse.setStatusCode(response.statusCode());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runGET(String path, Map<String, Object> params){
        String token = Config.getValue("cashwiseToken");
        String url = Config.getValue("cashwiseBackend")+path;
        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println("Status code: " + response.getStatusCode());
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            customResponse = mapper.readValue(response.body().asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            System.out.println("Conversion from json to object was unsuccessful!");
        }
    }

    public static CustomResponse getCustomResponse(){
        return customResponse;
    }
}
