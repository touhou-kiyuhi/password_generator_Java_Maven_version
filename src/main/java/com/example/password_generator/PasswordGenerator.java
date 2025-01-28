package com.example.password_generator;

import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {

    private String password = "";
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
        
    }
}
