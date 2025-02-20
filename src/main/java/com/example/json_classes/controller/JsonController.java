package com.example.json_classes.controller;

import java.io.File;
import java.io.IOException;

import com.example.json_classes.format.Account;
import com.example.json_classes.format.PasswordsBackup;
import com.example.json_classes.type.JsonFormatType;
import com.example.json_classes.type.JsonControllerViewerType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum JsonController implements Controller {
    START;

    // Java object to JSON file - default compact-print
    private final ObjectMapper MAPPER = new ObjectMapper();
    // Setup a pretty printer with an indenter (indenter has 4 spaces in this case)
    private final DefaultPrettyPrinter.Indenter INDENTER = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
    private final DefaultPrettyPrinter PRINTER = new DefaultPrettyPrinter();

    JsonController() {
        PRINTER.indentObjectsWith(INDENTER);
        PRINTER.indentArraysWith(INDENTER);
    }

    @Override
    public Object jsonReader(String path, JsonFormatType jsonType) throws StreamReadException, DatabindException, IOException {
        // Convert JSON file to Java object
        switch (jsonType) {
            case ACCOUNT:
                Account account = MAPPER.readValue(new File(path), Account.class);
                return account;
            case PASSWORDSBACKUP:
                PasswordsBackup passwordsBackup = MAPPER.readValue(new File(path), PasswordsBackup.class);
                return passwordsBackup;
            default:
                // error
                System.out.println("ERROR!");
                return 0;
        }
    }

    @Override
    public void jsonWriter(String path, Object data) throws StreamWriteException, DatabindException, IOException {
        // notice: write Java object to JSON file with pretty print
        MAPPER.writer(PRINTER).writeValue(new File(path), data);
    }

    @Override
    public void jsonViewer(Object data, JsonControllerViewerType jsonviewerType) throws JsonProcessingException {
        switch (jsonviewerType) {
            case DEFAULT:
                // convert Java object to JSON string - default compact-print
                String jsonString = MAPPER.writeValueAsString(data);
                System.out.println(jsonString);
                break;
            case PRETTY:
                // convert Java object to JSON string - with json pretty-print
                String jsonStringPrettyPrint = MAPPER.writer(PRINTER).writeValueAsString(data);
                System.out.println(jsonStringPrettyPrint);
                break;
            default:
                // error
                System.out.println("ERROR!");
                break;
        }
    }
}
