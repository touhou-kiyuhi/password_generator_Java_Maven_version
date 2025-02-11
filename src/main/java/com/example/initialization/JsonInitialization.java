package com.example.initialization;

import java.io.IOException;

import com.example.jsonController.JsonController;
import com.example.jsonControllerInitialization.JsonControllerInit;
import com.example.jsonFormat.Account;
import com.example.jsonFormat.JsonType;
import com.example.jsonFormat.PasswordsBackup;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class JsonInitialization implements JsonControllerInit {

    public JsonInitialization() {}

    public void account_json_setup(String user, String password) throws StreamWriteException, DatabindException, IOException {
        Account account = new Account();
        account = account.createAccount(user, password);
        // json writer
        JsonController.START.jsonWriter(ACCOUNT_PATH, account);
        // json viewer
        Account data = (Account) JsonController.START.jsonReader(ACCOUNT_PATH, JsonType.ACCOUNT);
        JsonController.START.jsonViewer(data, JSONVIEWER_TYPE);
    }

    public void passwords_json_backup(String password) throws StreamWriteException, DatabindException, IOException {
        PasswordsBackup passwordsBackup = new PasswordsBackup();
        passwordsBackup = passwordsBackup.createPasswordsBackup(password);
        // json writer
        JsonController.START.jsonWriter(PASSWORDS_BACKUP_PATH, passwordsBackup);
        // json viewer
        PasswordsBackup data = (PasswordsBackup) JsonController.START.jsonReader(PASSWORDS_BACKUP_PATH, JsonType.PASSWORDSBACKUP);
        JsonController.START.jsonViewer(data, JSONVIEWER_TYPE);
    }
}
