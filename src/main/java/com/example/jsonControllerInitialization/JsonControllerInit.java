package com.example.jsonControllerInitialization;

import com.example.jsonController.JsonController;

public abstract class JsonControllerInit {
    protected final String ACCOUNT_PATH = "json_data/account.json";
    protected final String PASSWORDS_BACKUP_PATH = "json_data/passwords_backup.json";
    protected final JsonController JSON_CONTROLLER = new JsonController();
}
