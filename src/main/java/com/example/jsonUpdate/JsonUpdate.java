package com.example.jsonUpdate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.jsonControllerInitialization.JsonControllerInit;
import com.example.jsonFormat.Account;
import com.example.jsonFormat.PasswordsBackUp;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class JsonUpdate extends JsonControllerInit{

    private Account accountData;
    private PasswordsBackUp backUpData;

    public JsonUpdate() throws StreamReadException, DatabindException, IOException {
        super();
        this.accountData = (Account) JSON_CONTROLLER.jsonReader(ACCOUNT_PATH, 0);
        this.backUpData = (PasswordsBackUp) JSON_CONTROLLER.jsonReader(PASSWORDS_BACKUP_PATH, 1);
    }

    public void accountJsonUpdate(String new_password) throws StreamWriteException, DatabindException, IOException {
        Account account = new Account();
        String userName = accountData.getAccount().get("user");
        account = account.createAccount(userName, new_password);
        // json writer
        JSON_CONTROLLER.jsonWriter(ACCOUNT_PATH, account);
        // json viewer
        Account data = (Account) JSON_CONTROLLER.jsonReader(ACCOUNT_PATH, 0);
        JSON_CONTROLLER.jsonViewer(data, 1);
    }

    public void backupJsonUpdate(String new_password) throws StreamWriteException, DatabindException, IOException {
        PasswordsBackUp passwordsBackUp = new PasswordsBackUp();
        // 檢查新密碼是否存在於備份密碼中
        boolean dataExistedFlag = checkNew_passwordInBackUpDataPasswordsList(new_password);

        if (dataExistedFlag) {
            System.out.println("the password exists in the passwords_backup.json");
        } else {
            // 新增密碼到備份密碼
            passwordsBackUp = addPasswordsBackup(new_password);
            // json writer
            JSON_CONTROLLER.jsonWriter(PASSWORDS_BACKUP_PATH, passwordsBackUp);
        }
        // json viewer
        PasswordsBackUp data = (PasswordsBackUp) JSON_CONTROLLER.jsonReader(PASSWORDS_BACKUP_PATH, 1);
        JSON_CONTROLLER.jsonViewer(data, 1);
    }

    // 檢查新密碼是否存在於備份密碼中
    private boolean checkNew_passwordInBackUpDataPasswordsList(String new_password) {
        boolean dataExistedFlag = false;
        for (int i = 0; i < backUpData.getLength(); i++) {
            if (backUpData.getAccount_passwords().get(i).get("password").equals(new_password)) {
                dataExistedFlag = true;
                return dataExistedFlag;
            }
        }
        return dataExistedFlag;
    }
    // 新增密碼到備份密碼
    private PasswordsBackUp addPasswordsBackup(String new_password) {
        PasswordsBackUp passwordsBackUp = new PasswordsBackUp();

        int length = backUpData.getLength() + 1;
        passwordsBackUp.setLength(length);

        List<Map<String, Object>>account_passwords_List = backUpData.getAccount_passwords();
        Map<String, Object>account_passwords_List_Map = new HashMap<>();
        account_passwords_List_Map.put("number", length - 1);
        account_passwords_List_Map.put("password", new_password);
        account_passwords_List.add(account_passwords_List_Map);
        passwordsBackUp.setAccount_passwords(account_passwords_List);

        return passwordsBackUp;
    }
}
