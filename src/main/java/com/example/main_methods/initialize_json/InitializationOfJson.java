package com.example.main_methods.initialize_json;

import java.io.IOException;

import com.example.json_classes.controller.JsonController;
import com.example.json_classes.format.Account;
import com.example.json_classes.format.PasswordsBackup;
import com.example.json_classes.settings.JsonSettings;
import com.example.json_classes.type.JsonFormatType;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class InitializationOfJson implements JsonSettings {

    private Account account;
    private PasswordsBackup passwordsBackup;

    public InitializationOfJson() {
        this.account = new Account();
        this.passwordsBackup = new PasswordsBackup();
    }

    public void account_json_setup(String user, String password) throws StreamWriteException, DatabindException, IOException {
        account = account.createAccount(user, password);
        // json writer
        JsonController.START.jsonWriter(ACCOUNT_PATH, account);
        // json viewer
        Account data = (Account) JsonController.START.jsonReader(ACCOUNT_PATH, JsonFormatType.ACCOUNT);
        JsonController.START.jsonViewer(data, JSONVIEWER_TYPE);
    }

    public void passwords_json_backup(String password) throws StreamWriteException, DatabindException, IOException {
        passwordsBackup = passwordsBackup.createPasswordsBackup(password);
        // json writer
        JsonController.START.jsonWriter(PASSWORDS_BACKUP_PATH, passwordsBackup);
        // json viewer
        PasswordsBackup data = (PasswordsBackup) JsonController.START.jsonReader(PASSWORDS_BACKUP_PATH, JsonFormatType.PASSWORDSBACKUP);
        JsonController.START.jsonViewer(data, JSONVIEWER_TYPE);
    }
}
