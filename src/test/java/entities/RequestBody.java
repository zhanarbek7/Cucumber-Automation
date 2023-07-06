package entities;

import lombok.Data;

import java.util.List;

@Data
public class RequestBody {

    private String password;
    private boolean flag;
    private String name_tag;
    private String category_title;
    private String category_description;
    private String email;
    private String company_name;
    private String seller_name;
    private String phone_number;
    private String address;
    private String type_of_pay;
    private String bank_account_name;
    private String description;
    private int balance;
    private String product_title;
    private int product_price;
    private int service_type_id;
    private int category_id;
    private String product_description;
    private String client_name;
    private List<String> tags_id;

}
