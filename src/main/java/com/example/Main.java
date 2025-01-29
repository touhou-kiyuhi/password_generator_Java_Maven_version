package com.example;

import java.io.IOException;

import com.example.initialization.JsonInitialization;
import com.example.jsonUpdate.JsonUpdate;
import com.example.passwordGenerator.PasswordGenerator;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {
    public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
        // Initialization
        String user = "Test_TT.User";
        String password = "abcdeABCDE1234";
        initialization(user, password);

        // Passwords Generator
        int passwordLength = 20;
        passwordGenerator(passwordLength);

        // To update the .json of the account and passwords_backup because we save them in json file
        password = "edcbaEDCBA4321";
        updateJson(password);
    }

    public static void initialization(String user, String password) throws StreamWriteException, DatabindException, IOException {
        JsonInitialization init = new JsonInitialization();
        init.account_json_setup(user, password);
        init.passwords_json_backup(password);
    }

    public static void passwordGenerator(int passwordLength) throws StreamReadException, DatabindException, IOException {
        PasswordGenerator pwdGenerator = new PasswordGenerator();
        pwdGenerator.setPassword(passwordLength);
        String password = pwdGenerator.getPassword();
        System.out.println(password);
    }

    public static void updateJson(String new_password) throws StreamReadException, DatabindException, IOException {
        JsonUpdate update = new JsonUpdate();
        update.accountJsonUpdate(new_password);
        update.backupJsonUpdate(new_password);
    }
}
