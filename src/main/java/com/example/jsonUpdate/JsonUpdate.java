package com.example.jsonUpdate;

import java.io.IOException;

import com.example.jsonController.JsonController;
import com.example.jsonControllerInitialization.JsonControllerInit;
import com.example.jsonFormat.Account;
import com.example.jsonFormat.JsonType;
import com.example.jsonFormat.PasswordsBackup;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class JsonUpdate implements JsonControllerInit{

    private Account accountData;
    private PasswordsBackup backupData;

    public JsonUpdate() throws StreamReadException, DatabindException, IOException {
        super();
        this.accountData = (Account) JsonController.START.jsonReader(ACCOUNT_PATH, JsonType.ACCOUNT);
        this.backupData = (PasswordsBackup) JsonController.START.jsonReader(PASSWORDS_BACKUP_PATH, JsonType.PASSWORDSBACKUP);
    }

    public void accountJsonUpdate(String new_password) throws StreamWriteException, DatabindException, IOException {
        Account account = new Account();
        String userName = accountData.getAccount().get("user");
        account = account.createAccount(userName, new_password);
        // json writer
        JsonController.START.jsonWriter(ACCOUNT_PATH, account);
        // json viewer
        Account data = (Account) JsonController.START.jsonReader(ACCOUNT_PATH, JsonType.ACCOUNT);
        JsonController.START.jsonViewer(data, JSONVIEWER_TYPE);
    }

    public void backupJsonUpdate(String new_password) throws StreamWriteException, DatabindException, IOException {
        PasswordsBackup passwordsBackup = new PasswordsBackup();
        // 檢查新密碼是否存在於備份密碼中
        boolean dataExistedFlag = checkNew_passwordInBackupDataPasswordsList(new_password);

        if (dataExistedFlag) {
            System.out.println("the password exists in the passwords_backup.json");
        } else {
            // 新增密碼到備份密碼
            passwordsBackup = passwordsBackup.addPasswordsBackup(backupData, new_password);
            // json writer
            JsonController.START.jsonWriter(PASSWORDS_BACKUP_PATH, passwordsBackup);
        }
        // json viewer
        PasswordsBackup data = (PasswordsBackup) JsonController.START.jsonReader(PASSWORDS_BACKUP_PATH, JsonType.PASSWORDSBACKUP);
        JsonController.START.jsonViewer(data, JSONVIEWER_TYPE);
    }

    // 檢查新密碼是否存在於備份密碼中
    private boolean checkNew_passwordInBackupDataPasswordsList(String new_password) {
        boolean dataExistedFlag = false;
        for (int i = 0; i < backupData.getLength(); i++) {
            if (backupData.getAccount_passwords().get(i).get("password").equals(new_password)) {
                dataExistedFlag = true;
                return dataExistedFlag;
            }
        }
        return dataExistedFlag;
    }
}
