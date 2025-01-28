package com.example.initialization;

import java.io.IOException;

import com.example.jsonController.JsonController;
import com.example.jsonFormat.Account;
import com.example.jsonFormat.PasswordsBackUp;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class JsonInitialization {

    private final String ACCOUNT_PATH = "json_data/account.json";
    private final String PASSWORDS_BACKUP_PATH = "json_data/passwords_backup.json";
    private final JsonController JSON_CONTROLLER = new JsonController();

    public JsonInitialization() {}

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
        PasswordsBackUp passwordsBackUp = new PasswordsBackUp();
        passwordsBackUp = passwordsBackUp.createPasswordsBackup(password);
        // json writer
        JSON_CONTROLLER.jsonWriter(PASSWORDS_BACKUP_PATH, passwordsBackUp);
        // json viewer
        PasswordsBackUp data = (PasswordsBackUp) JSON_CONTROLLER.jsonReader(PASSWORDS_BACKUP_PATH, 1);
        JSON_CONTROLLER.jsonViewer(data, 1);
    }
}
