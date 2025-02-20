package com.example.json_classes.settings;

public interface JsonSettings {
    // Json Path 
    public final String ACCOUNT_PATH = "json_data/account.json";
    public final String PASSWORDS_BACKUP_PATH = "json_data/passwords_backup.json";
    // Json Printer style 
    public final JsonViewerType JSONVIEWER_TYPE = JsonViewerType.PRETTY;
}
