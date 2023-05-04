package tests;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

public class JsonPathTest {
    String json = "{\n" +
            "    \"firstName\": \"John\",\n" +
            "    \"lastName\": \"doe\",\n" +
            "    \"age\": 26,\n" +
            "    \"address\": {\n" +
            "        \"streetAddress\": \"naist street\",\n" +
            "        \"city\": \"Nara\",\n" +
            "        \"postalCode\": \"630-0192\"\n" +
            "    },\n" +
            "    \"phoneNumbers\": [\n" +
            "        {\n" +
            "            \"type\": \"iPhone\",\n" +
            "            \"number\": \"0123-4567-8888\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"type\": \"home\",\n" +
            "            \"number\": \"0123-4567-8910\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    @Test
    public void jsonPath(){
        System.out.println(JsonPath.from(json).prettyPrint());
        System.out.println(JsonPath.from(json).get(".phoneNumbers[1].type").toString());
    }

}
