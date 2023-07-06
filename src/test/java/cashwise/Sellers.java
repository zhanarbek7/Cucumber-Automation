package cashwise;

import com.github.javafaker.Faker;
import entities.RequestBody;
import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;

public class Sellers {


    @Test
    public void getSeller(){
        String path = "/api/myaccount/sellers/359";
        APIRunner.runGET(path);

        Assert.assertNotNull(APIRunner.getCustomResponse().getSeller_name());
        Assert.assertNotNull(APIRunner.getCustomResponse().getCompany_name());
        Assert.assertNotNull(APIRunner.getCustomResponse().getEmail());
        Assert.assertNotNull(APIRunner.getCustomResponse().getPhone_number());

        Assert.assertFalse(APIRunner.getCustomResponse().getSeller_name().trim().isEmpty());
        Assert.assertFalse(APIRunner.getCustomResponse().getCompany_name().trim().isEmpty());
        Assert.assertFalse(APIRunner.getCustomResponse().getEmail().trim().isEmpty());
        Assert.assertFalse(APIRunner.getCustomResponse().getPhone_number().trim().isEmpty());

        Assert.assertTrue(APIRunner.getCustomResponse().getEmail().
                contains("@") && APIRunner.getCustomResponse().getEmail().contains("."));

        String phoneNumber = APIRunner.getCustomResponse().getPhone_number();
        phoneNumber = phoneNumber.replace("+", "");
        phoneNumber = phoneNumber.replace("-", "");
        phoneNumber = phoneNumber.replace(" ", "");

        Assert.assertTrue(phoneNumber.length() == 10 || phoneNumber.length() == 11);
    }

    @Test
    public void verifySellers(){
        String path = "/api/myaccount/sellers/all";
        APIRunner.runGETList(path);
        System.out.println("Number of sellers: "+APIRunner.getCustomResponseList().length);
        for(int i = 0; i < APIRunner.getCustomResponseList().length; i++){
            System.out.printf(" i = %d, seller name = %s\n", (i+1), APIRunner.getCustomResponseList()[i].getSeller_name());
            System.out.printf(" i = %d, company name = %s\n\n\n", (i+1), APIRunner.getCustomResponseList()[i].getCompany_name());
            Assert.assertNotNull(APIRunner.getCustomResponseList()[i].getSeller_name());
            Assert.assertNotNull(APIRunner.getCustomResponseList()[i].getCompany_name());
        }
    }

    @Test
    public void createSeller(){
        String path = "/api/myaccount/sellers";
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("Amazon");
        requestBody.setSeller_name("Eshton Ruler");
        requestBody.setEmail("eshton@gmail.com");
        requestBody.setPhone_number("982497239");
        requestBody.setAddress("Online Store");
        APIRunner.runPost(path, requestBody);
        System.out.println(APIRunner.getCustomResponse().getJsonString());
    }

    @Test
    public void create10Sellers(){
        String path = "/api/myaccount/sellers";
        Faker faker = new Faker();
        RequestBody requestBody = new RequestBody();
        for(int i = 0; i < 10; i++){
            requestBody.setCompany_name(faker.company().name());
            requestBody.setSeller_name(faker.name().fullName());
            requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
            requestBody.setEmail(faker.internet().emailAddress());
            requestBody.setAddress(faker.address().fullAddress());

            APIRunner.runPost(path, requestBody);
        }
    }
}
