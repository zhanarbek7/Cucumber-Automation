package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Holiday;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Serial_Deserial {

    @Test
    public void test() throws JsonProcessingException {
        String url = "https://date.nager.at/api/v2/publicholidays/2020/US";
        Response response = RestAssured.given().get(url);
//        Holiday[] holidays1 = response.getBody().as(Holiday[].class);
//        List<Holiday> holidayListWithAs = Arrays.asList(holidays1);


        ObjectMapper objectMapper = new ObjectMapper();
        Holiday[] holidays2 = objectMapper.readValue(response.asString(), Holiday[].class);
        List<Holiday> holidayListWithMapper = Arrays.asList(holidays2);

        System.out.println(holidayListWithMapper);
    }


}
