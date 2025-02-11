package com.example.jsonController;

import java.io.File;
import java.io.IOException;

import com.example.jsonFormat.Account;
import com.example.jsonFormat.JsonType;
import com.example.jsonFormat.PasswordsBackup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonController {
    // Java object to JSON file - default compact-print
    private final ObjectMapper MAPPER = new ObjectMapper();
    // Setup a pretty printer with an indenter (indenter has 4 spaces in this case)
    private final DefaultPrettyPrinter.Indenter INDENTER = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
    private final DefaultPrettyPrinter PRINTER = new DefaultPrettyPrinter();

    public JsonController() {
        PRINTER.indentObjectsWith(INDENTER);
        PRINTER.indentArraysWith(INDENTER);
    }

    public Object jsonReader(String path, JsonType jsonType) throws StreamReadException, DatabindException, IOException {
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

    public void jsonWriter(String path, Object data) throws StreamWriteException, DatabindException, IOException {
        MAPPER.writer(PRINTER).writeValue(new File(path), data);
        // notice: write Java object to JSON file with pretty print
    }

    public void jsonViewer(Object data, JsonViewerType jsonViewerType) throws JsonProcessingException {
        switch (jsonViewerType) {
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

    public enum JsonViewerType{
        DEFAULT, PRETTY
    }
}
