package com.example.password_generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.jsonControllerInitialization.JsonControllerInit;
import com.example.jsonFormat.Account;
import com.example.jsonFormat.PasswordsBackUp;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class PasswordGenerator extends JsonControllerInit {
    // 使用者名稱、備份密碼 元素
    private Account account;
    private String userName;
    private PasswordsBackUp passwordsBackup; 
    private List<String> passwordsBackupElements = new ArrayList<>();

    // 密碼
    private String password = "";
    // 密碼長度
    private int passwordLength = 14;
    // 長度至少為 14 個字元
    private int passwordLengthLimit = 14;
    // 英文大寫字元 (A 到 Z)：65 ~ 90
    private int upperAlphabetsLength = 5;
    private List<Character> upperAlphabets = new ArrayList<>();
    // 英文小寫字元 (a 到 z)：97 ~ 122
    private int lowerAlphabetsLength = 5;
    private List<Character> lowerAlphabets = new ArrayList<>();
    // 10 進位數字 (0 到 9)：48 ~ 57
    private int numbersLength = 4;
    private List<Character> numbers = new ArrayList<>();
    // 非英文字母字元 (例如: !、$、#、%)：32 ~ 126 ，不包含 48 ~ 57 、 65 ~ 90 、 97 ~ 122
    private List<Character> symbolsElements = new ArrayList<>();
    private int symbolsLength = 0;
    private List<Character> symbols = new ArrayList<>();

    // Constructor
    public PasswordGenerator() throws StreamReadException, DatabindException, IOException {
        super();
        this.account = (Account) JSON_CONTROLLER.jsonReader(ACCOUNT_PATH, 0);
        this.passwordsBackup = (PasswordsBackUp) JSON_CONTROLLER.jsonReader(PASSWORDS_BACKUP_PATH, 1);
    }

    // Getter, Setter
    public String getUserName() {
        return userName;
    }
    private void setUserName() {
        this.userName = account.getAccount().get("user");
    }
    // 備份密碼元素
    public List<String> getPasswordsBackupElements() {
        return passwordsBackupElements;
    }
    private void setPasswordsBackupElements() {
        for (int i = 0; i < passwordsBackup.getLength(); i++) {
            String password = (String) (passwordsBackup.getAccount_passwords().get(i)).get("password");
            this.passwordsBackupElements.add(password);
        }
    }

    // 英文大寫字元 (A 到 Z)：65 ~ 90
    public List<Character> getUpperAlphabets() {
        return upperAlphabets;
    }
    private void setUpperAlphabets() {
        
    }
    // 英文小寫字元 (a 到 z)：97 ~ 122
    public List<Character> getLowerAlphabets() {
        return lowerAlphabets;
    }
    private void setLowerAlphabets() {
        
    }
    // 10 進位數字 (0 到 9)：48 ~ 57
    public List<Character> getNumbers() {
        return numbers;
    }
    private void setNumbers() {
        
    }
    // 非英文字母字元 (例如: !、$、#、%)：32 ~ 126 ，不包含 48 ~ 57 、 65 ~ 90 、 97 ~ 122
    public List<Character> getSymbols() {
        return symbols;
    }
    private void setSymbols() {
        
    }

    public String getPassword() {
        return password;
    }
    public void setPassword() {
        setUserName();
        setPasswordsBackupElements();
    }
}
