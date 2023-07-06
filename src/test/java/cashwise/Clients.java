package cashwise;

import org.junit.Test;
import utilities.APIRunner;

public class Clients {

    @Test
    public void getClient(){
        String path = "/api/myaccount/clients/572";
        APIRunner.runGET(path);
        System.out.println(APIRunner.getCustomResponse().getCompany_name());
        System.out.println(APIRunner.getCustomResponse().getClient_name());
        System.out.println(APIRunner.getCustomResponse().getEmail());
        for (int i = 0; i < APIRunner.getCustomResponse().getTags().size(); i++){
            System.out.println("Tag name: "+APIRunner.getCustomResponse().getTags().get(i).getNameTag());
            System.out.println("Company name: "+APIRunner.getCustomResponse().getTags().get(i).getCompany().getCompanyName());
        }
    }

}
