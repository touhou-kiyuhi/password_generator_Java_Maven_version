package com.example.jsonControllerInitialization;

import com.example.jsonController.JsonController.JsonViewerType;

public interface JsonControllerInit {
    public final String ACCOUNT_PATH = "json_data/account.json";
    public final String PASSWORDS_BACKUP_PATH = "json_data/passwords_backup.json";
    
    public final JsonViewerType JSONVIEWER_TYPE = JsonViewerType.PRETTY;
}
