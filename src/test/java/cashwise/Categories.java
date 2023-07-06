package cashwise;

import entities.CustomResponse;
import entities.RequestBody;
import org.junit.Test;
import utilities.APIRunner;

import java.util.Arrays;

public class Categories {

    @Test
    public void updateCategories(){
        String getCategoryPath = "/api/myaccount/categories";
        APIRunner.runGETList(getCategoryPath);
        System.out.println("Number of categories: "+APIRunner.getCustomResponseList().length);
        for (int i = 0; i < APIRunner.getCustomResponseList().length; i++){
            String updatePath = "/api/myaccount/categories/" + APIRunner.getCustomResponseList()[i].getCategory_id();
            RequestBody requestBody = new RequestBody();
            requestBody.setCategory_title(APIRunner.getCustomResponseList()[i].getCategory_title());
            requestBody.setCategory_description("For check purpose");
            requestBody.setFlag(false);
            APIRunner.runPUT(updatePath, requestBody);
        }
    }

    @Test
    public void deleteCategory(){
        String categoryPath = "/api/myaccount/categories";
        APIRunner.runGETList(categoryPath);
        System.out.println("Number of categories: "+APIRunner.getCustomResponseList().length);

        for (int i = 0; i < APIRunner.getCustomResponseList().length; i++) {
                System.out.println("Category ID: "+APIRunner.getCustomResponseList()[i].getCategory_id());
                APIRunner.runDELETE(categoryPath+"/"+APIRunner.getCustomResponseList()[i].getCategory_id()+"");
        }
    }
}
