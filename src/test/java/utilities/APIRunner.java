package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

    public static void runGETList(String path){
        String token = Config.getValue("cashwiseToken");
        String url = Config.getValue("cashwiseBackend") + path;
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("GET Status: "+ response.statusCode());
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            responseList = mapper.readValue(response.body().asString(), CustomResponse[].class);
        } catch (JsonProcessingException e) {
            System.out.println("Couldn't convert JSON to CustomResponse");
        }
    }

    public static void runDELETE(String path){
        String token = Config.getValue("cashwiseToken");
        String url = Config.getValue("cashwiseBackend") + path;
        Response response = RestAssured.given().auth().oauth2(token).delete(url);
        System.out.println("DELETE Status: "+response.statusCode());
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            customResponse = mapper.readValue(response.body().asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            System.out.println("Couldn't convert JSON to CustomResponse");
        }
    }

    public static void runPUT(String path, RequestBody requestBody){
        String url = Config.getValue("cashwiseBackend") + path;
        String token = Config.getValue("cashwiseToken");
        Response response = RestAssured.given().auth().oauth2(token).
                contentType(ContentType.JSON).body(requestBody).put(url);
        System.out.println("PUT Status: " + response.statusCode());
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            customResponse.setJsonString(response.asString());
            customResponse.setStatusCode(response.statusCode());
        } catch (JsonProcessingException e) {
            System.out.println("Couldn't map json to Custom Response");
        }
    }

    public static void runPost(String path, RequestBody requestBody) {
        String token = Config.getValue("cashwiseToken");
        String url = Config.getValue("cashwiseBackend") + path;
        Response response = RestAssured.given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody).post(url);
        ObjectMapper m = new ObjectMapper();
        try {
            System.out.println(m.writeValueAsString(requestBody));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("POST status: "+response.statusCode());
        try{
            ObjectMapper mapper = new ObjectMapper();
            customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            customResponse.setStatusCode(response.statusCode());
            customResponse.setJsonString(response.body().asString());
        } catch (JsonProcessingException e) {
            System.out.println("Couldn't parse json to object!");
        }
    }

    public static CustomResponse getCustomResponse(){
        return customResponse;
    }

    public static CustomResponse[] getCustomResponseList(){
        return  responseList;
    }
}
