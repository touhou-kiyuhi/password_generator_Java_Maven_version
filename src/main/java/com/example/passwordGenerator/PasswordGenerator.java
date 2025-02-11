package com.example.passwordGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.jsonControllerInitialization.JsonControllerInit;
import com.example.jsonFormat.Account;
import com.example.jsonFormat.JsonType;
import com.example.jsonFormat.PasswordsBackup;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class PasswordGenerator implements JsonControllerInit {

    private final Random RANDOM = new Random();
    // 使用者名稱、備份密碼 元素
    private Account account;
    private String userName;
    private PasswordsBackup passwordsBackup; 
    private List<String> passwordsBackupElements = new ArrayList<>();

    // 密碼
    private List<Character> passwordList = new ArrayList<>();
    private String password = "";
    // 密碼長度
    private int passwordLength;
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
    private int symbolsLength;
    private List<Character> symbols = new ArrayList<>();

    // Constructor
    public PasswordGenerator() throws StreamReadException, DatabindException, IOException {
        super();
        this.account = (Account) JSON_CONTROLLER.jsonReader(ACCOUNT_PATH, JsonType.ACCOUNT);
        this.passwordsBackup = (PasswordsBackup) JSON_CONTROLLER.jsonReader(PASSWORDS_BACKUP_PATH, JsonType.PASSWORDSBACKUP);
        setSymbolsElements();
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
        generator(this.upperAlphabetsLength, this.upperAlphabets, 65, 90);
    }
    // 英文小寫字元 (a 到 z)：97 ~ 122
    public List<Character> getLowerAlphabets() {
        return lowerAlphabets;
    }
    private void setLowerAlphabets() {
        generator(this.lowerAlphabetsLength, this.lowerAlphabets, 97, 122);
    }
    // 10 進位數字 (0 到 9)：48 ~ 57
    public List<Character> getNumbers() {
        return numbers;
    }
    private void setNumbers() {
        generator(this.numbersLength, this.numbers, 48, 57);
    }
    // 非英文字母字元 (例如: !、$、#、%)：32 ~ 126 ，不包含 48 ~ 57 、 65 ~ 90 、 97 ~ 122
    public List<Character> getSymbolsElements() {
        return symbolsElements;
    }
    private void setSymbolsElements() {
        for (int i = 32; i < 126 + 1; i++) {
            if ((48 <= i && i <= 57) || (65 <= i && i <= 90) || (97 <= i && i <= 122)) {
                continue;
            }
            this.symbolsElements.add((char) i);
        }
    }
    public int getSymbolsLength() {
        return symbolsLength;
    }
    private void setSymbolsLength() {
        this.symbolsLength = passwordLength - passwordLengthLimit;
    }
    public List<Character> getSymbols() {
        return symbols;
    }
    private void setSymbols() {
        while (true) {
            for (int i = 0; i < symbolsLength; i++) {
                symbols.add(symbolsElements.get(RANDOM.nextInt(symbolsElements.size())));
            }
            // 確認一字元不能重複出現三次
            if (checkCharactersNotTriplicate(symbols)) {
                break;
            }
            symbols.clear();
        }
    }

    // 密碼 
    public List<Character> getPasswordList() {
        return passwordList;
    }
    private void setPasswordList() {
        passwordList.addAll(lowerAlphabets);
        passwordList.addAll(upperAlphabets);
        passwordList.addAll(numbers);
        passwordList.addAll(symbols);
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(int passwordLength) {
        // 密碼長度
        setPasswordLength(passwordLength);
        // 非英文字母字元長度
        setSymbolsLength();

        // 使用者名稱、備份密碼 元素
        setUserName();
        setPasswordsBackupElements();
        while (true) {
            // 密碼不為空字串
            // 密碼中，不可包含帳號相關字眼
            // 確認密碼不存在於備份密碼中
            if (password != "" && checkPasswordSubstringsNotInUserName() && checkPasswordNotInPasswordsBackupElements()) {
                break;
            }
            // 英文大寫字元 (A 到 Z)、英文小寫字元 (a 到 z)、10 進位數字 (0 到 9)、非英文字母字元 (例如: !、$、#、%)
            setUpperAlphabets();
            setLowerAlphabets();
            setNumbers();
            setSymbols();

            setPasswordList();
            for (char c: passwordList) {
                String s = String.valueOf(c);
                this.password += s;
            }
        }
    }
    public int getPasswordLength() {
        return passwordLength;
    }
    public void setPasswordLength(int passwordLength) {
        if (passwordLength < passwordLengthLimit) {
            this.passwordLength = passwordLengthLimit;
        } else {
            this.passwordLength = passwordLength;
        }
    }

    // Others
    // 密碼中，不可包含帳號相關字眼
    private boolean checkPasswordSubstringsNotInUserName() {
        String passwordSubstring;
        for (int i = 0; i < passwordLength - 3; i++) {
            passwordSubstring = password.substring(i, i + 3);
            if (userName.contains(passwordSubstring)) {
                return false;
            } 
        }
        return true;
    }
    // 確認密碼不存在於備份密碼中
    private boolean checkPasswordNotInPasswordsBackupElements() {
        for (String s: passwordsBackupElements) {
            if (password.contains(s)) {
                return false;
            }
        }
        return true;
    }
    // 確認一字元不能重複出現三次
    private boolean checkCharactersNotTriplicate(List<Character> lst) {
        // 建立 Map 計算字母出現次數
        Map<Character, Integer> lstMap = new HashMap<>();
        for (char c: lst) {
            if (lstMap.isEmpty()) {
                lstMap.put(c, 1);
            } else if (lstMap.get(c) == null) {
                lstMap.put(c, 1);
            } else {
                lstMap.put(c, lstMap.get(c) + 1);
            }
        }
        // 檢查次數是否大於 2
        for (char k: lstMap.keySet()) {
            if (lstMap.get(k) > 2) {
                return false;
            }
        }
        return true;
    }
    // 英文大寫字元 (A 到 Z)、英文小寫字元 (a 到 z)、10 進位數字 (0 到 9) 生成器
    private void generator(int length, List<Character> lst, int rangeFirst, int rangeLast) {
        while (true) {
            for (int i = 0; i < length; i++) {
                lst.add((char) ((int) (RANDOM.nextInt(rangeLast - rangeFirst + 1) + rangeFirst)));
            }
            // 確認一字元不能重複出現三次
            if (checkCharactersNotTriplicate(lst)) {
                break;
            }
            lst.clear();
        }
    }
}
