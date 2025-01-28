package com.example.jsonFormat;

import java.util.HashMap;
import java.util.Map;

// {
//     "account": {
//         "user": "Test_TT.User",
//         "password": "abcdeABCDE1234"
//     },
//     "domain_name": {
//         "at": "@",
//         "email": "example.com"
//     }
// }
public class Account {
    private String domain_name_at = "@";
    private String domain_name_email = "example.com";

    private Map<String, String>account;
    private Map<String, String>domain_name;

    // Constructor
    public Account() {}

    public Account(Map<String, String>account, Map<String, String>domain_name) {
        this.account = account;
        this.domain_name = domain_name;
    }

    // Getter, Setter
    public Map<String, String> getAccount() {
        return account;
    }

    public void setAccount(Map<String, String>account) {
        this.account = account;
    }

    public Map<String, String> getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(Map<String, String>domain_name) {
        this.domain_name = domain_name;
    }
    
    // Creator 
    public Account createAccount(String user, String password) {
        Account account = new Account();

        Map<String, String>account_Map = new HashMap<>();
        account_Map.put("user", user);
        account_Map.put("password", password);
        account.setAccount(account_Map);

        Map<String, String>domain_name_Map = new HashMap<>();
        domain_name_Map.put("at", domain_name_at);
        domain_name_Map.put("email", domain_name_email);
        account.setDomain_name(domain_name_Map);

        return account;
    }
}
