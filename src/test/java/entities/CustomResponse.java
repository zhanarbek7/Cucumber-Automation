package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {

    private String jwt_token;
    private String message;
    private int category_id;
    private String category_title;
    private String category_description;
    private String client_name;
    private String company_name;
    private String seller_name;
    private boolean income;
    private String bank_account_name;
    private String id;
    private int balance;
    private String phone_number;
    private String email;
    private String jsonString;
    private int statusCode;
    private List<Universal> tags;
    private List<Universal> responses;

}
