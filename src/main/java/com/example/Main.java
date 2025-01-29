package com.example;

import java.io.IOException;
import java.util.List;

import com.example.initialization.JsonInitialization;
import com.example.password_generator.PasswordGenerator;
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
        passwordGenerator();
        // To update the .json of welltake password and backup because we save them in json file
    }

    public static void initialization(String user, String password) throws StreamWriteException, DatabindException, IOException {
        JsonInitialization init = new JsonInitialization();
        init.account_json_setup(user, password);
        init.passwords_json_backup(password);
    }

    public static void passwordGenerator() throws StreamReadException, DatabindException, IOException {
        PasswordGenerator pwdGenerator = new PasswordGenerator();
        pwdGenerator.setPassword();

        String userName = pwdGenerator.getUserName();
        System.out.println(userName);
        List<String> passwordBackUpElements = pwdGenerator.getPasswordsBackupElements();
        System.out.println(passwordBackUpElements);
    }
}
