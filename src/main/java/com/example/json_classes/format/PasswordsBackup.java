package com.example.json_classes.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// {
//     "length": 1,
//     "account_passwords": [
//         {
//             "number": 0,
//             "password": "abcdeABCDE1234"
//         }
//     ]
// }
public class PasswordsBackup {
    private int length;
    private List<Map<String, Object>>account_passwords;

    // Constructor
    public PasswordsBackup() {}

    public PasswordsBackup(int length, List<Map<String, Object>>account_passwords) {
        this.length = length;
        this.account_passwords = account_passwords;
    }

    // Getter, Setter
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Map<String, Object>> getAccount_passwords() {
        return account_passwords;
    }

    public void setAccount_passwords(List<Map<String, Object>>account_passwords) {
        this.account_passwords = account_passwords;
    }

    // Creator 
    public PasswordsBackup createPasswordsBackup(String password) {
        PasswordsBackup passwordsBackup = new PasswordsBackup();

        length = 1;
        passwordsBackup.setLength(length);

        List<Map<String, Object>>account_passwords_List = new ArrayList<>();
        Map<String, Object>account_passwords_List_Map = new HashMap<>();
        account_passwords_List_Map.put("number", 0);
        account_passwords_List_Map.put("password", password);
        account_passwords_List.add(account_passwords_List_Map);
        passwordsBackup.setAccount_passwords(account_passwords_List);

        return passwordsBackup;
    }

    // 新增密碼到備份密碼
    public PasswordsBackup addPasswordsBackup(PasswordsBackup backupData, String new_password) {
        PasswordsBackup passwordsBackup = new PasswordsBackup();

        int length = backupData.getLength() + 1;
        passwordsBackup.setLength(length);

        List<Map<String, Object>>account_passwords_List = backupData.getAccount_passwords();
        Map<String, Object>account_passwords_List_Map = new HashMap<>();
        account_passwords_List_Map.put("number", length - 1);
        account_passwords_List_Map.put("password", new_password);
        account_passwords_List.add(account_passwords_List_Map);
        passwordsBackup.setAccount_passwords(account_passwords_List);

        return passwordsBackup;
    }
}
