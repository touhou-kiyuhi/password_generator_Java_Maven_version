package com.example;

import java.io.IOException;

import com.example.main_methods.initialize_json.InitializationOfJson;
import com.example.main_methods.password_generator.PasswordGenerator;
import com.example.main_methods.update_json.UpdateOfJson;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {
    public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
        // Initialization
        String user = "Test_TT.User";
        String password = "abcdeABCDE1234";
        initialize_json(user, password);

        // Passwords Generator
        int passwordLength = 20;
        password_generator(passwordLength);

        // To update the .json of the account and passwords_backup because we save them in json file
        password = "edcbaEDCBA4321";
        update_json(password);
    }

    public static void initialize_json(String user, String password) throws StreamWriteException, DatabindException, IOException {
        InitializationOfJson init = new InitializationOfJson();
        init.setupAccountJson(user, password);
        init.backupPasswordsJson(password);
    }

    public static void password_generator(int passwordLength) throws StreamReadException, DatabindException, IOException {
        PasswordGenerator generator = new PasswordGenerator();
        generator.setPassword(passwordLength);
        String password = generator.getPassword();
        System.out.println(password);
    }

    public static void update_json(String new_password) throws StreamReadException, DatabindException, IOException {
        UpdateOfJson update = new UpdateOfJson();
        update.updateAccountJson(new_password);
        update.updateBackupJson(new_password);
    }
}