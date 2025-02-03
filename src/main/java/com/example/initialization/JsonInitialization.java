package com.example.initialization;

import java.io.IOException;

import com.example.jsonControllerInitialization.JsonControllerInit;
import com.example.jsonFormat.Account;
import com.example.jsonFormat.PasswordsBackup;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class JsonInitialization extends JsonControllerInit {

    public JsonInitialization() {
        super();
    }

    public void account_json_setup(String user, String password) throws StreamWriteException, DatabindException, IOException {
        Account account = new Account();
        account = account.createAccount(user, password);
        // json writer
        JSON_CONTROLLER.jsonWriter(ACCOUNT_PATH, account);
        // json viewer
        Account data = (Account) JSON_CONTROLLER.jsonReader(ACCOUNT_PATH, 0);
        JSON_CONTROLLER.jsonViewer(data, 1);
    }

    public void passwords_json_backup(String password) throws StreamWriteException, DatabindException, IOException {
        PasswordsBackup passwordsBackup = new PasswordsBackup();
        passwordsBackup = passwordsBackup.createPasswordsBackup(password);
        // json writer
        JSON_CONTROLLER.jsonWriter(PASSWORDS_BACKUP_PATH, passwordsBackup);
        // json viewer
        PasswordsBackup data = (PasswordsBackup) JSON_CONTROLLER.jsonReader(PASSWORDS_BACKUP_PATH, 1);
        JSON_CONTROLLER.jsonViewer(data, 1);
    }
}
