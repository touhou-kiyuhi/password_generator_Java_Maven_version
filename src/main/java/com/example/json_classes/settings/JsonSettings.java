package com.example.json_classes.settings;

import com.example.json_classes.type.JsonControllerViewerType;

public interface JsonSettings {
    // Json Path 
    public final String ACCOUNT_PATH = "json_data/account.json";
    public final String PASSWORDS_BACKUP_PATH = "json_data/passwords_backup.json";
    // Json Printer style 
    public final JsonControllerViewerType JSONVIEWER_TYPE = JsonControllerViewerType.PRETTY;
}
