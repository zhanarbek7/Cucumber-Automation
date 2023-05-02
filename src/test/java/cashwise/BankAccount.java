package cashwise;

import com.github.javafaker.Faker;
import entities.RequestBody;
import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankAccount {


    @Test
    // This test doesn't work because
    // Under the hood this api returns 500 itself
    // when we try to delete one
    public void deleteBankAccounts(){
        String bankAccountsPath = "/api/myaccount/bankaccount";
        APIRunner.runGETList(bankAccountsPath);
        System.out.println(APIRunner.getCustomResponseList().length);
        for(int i = 0; i < APIRunner.getCustomResponseList().length; i++){
            int balance = APIRunner.getCustomResponseList()[i].getBalance();
            if( balance < 500){
                String id = APIRunner.getCustomResponseList()[i].getId();
                String path = "/api/myaccount/bankaccount/" + id;
                APIRunner.runDELETE(path);
            }
        }
        System.out.println("After Deletion");
        APIRunner.runGETList(bankAccountsPath);
        for(int i = 0; i < APIRunner.getCustomResponseList().length; i++){
            System.out.println("Bank name: " + APIRunner.getCustomResponseList()[i].getBank_account_name());
            System.out.println("Balance: " + APIRunner.getCustomResponseList()[i].getBalance());
            System.out.println();
        }
    }

    @Test
    public void getBankAccount(){
        String path = "/api/myaccount/bankaccount/1";
        APIRunner.runGET(path);
        Assert.assertEquals(200, APIRunner.getCustomResponse().getStatusCode());
    }

    @Test
    public void createBankAccount(){
        String path = "/api/myaccount/bankaccount";
        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("BANK");
        requestBody.setBank_account_name("KGZ BANK INCORPORATED 2");
        requestBody.setBalance(1500);
        requestBody.setDescription("KZ Bank credit account");

        APIRunner.runPost(path, requestBody);

        System.out.println(APIRunner.getCustomResponse().getStatusCode());
        System.out.println(APIRunner.getCustomResponse().getJsonString());
    }

    @Test
    public void create13Account(){
        String path = "/api/myaccount/bankaccount";
        List<String> payTypes = new ArrayList<>(List.of("Electronic_Money_Transfer", "BANK","CASH"));
        Random random = new Random();
        Faker faker = new Faker();
        for(int i = 0; i < 13; i ++){
            RequestBody requestBody = new RequestBody();
            // for getting typeOfPay out of payTypes
            int index = random.nextInt(payTypes.size());
            requestBody.setType_of_pay(payTypes.get(index));
            //get some name from faker examples
            requestBody.setBank_account_name(faker.company().name());
            int balance = random.nextInt(200, 5000);
            requestBody.setBalance(balance);
            requestBody.setDescription("CREATED 13 banks");
            APIRunner.runPost(path, requestBody);
            System.out.println("Status creation "+ (i+1) + ": "+APIRunner.getCustomResponse().getStatusCode());
            System.out.println();
        }
    }
}
