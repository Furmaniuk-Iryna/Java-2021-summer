package com.company.model.entity;

public class NotUniqueNicknameException extends Exception {
    private String nicknameData;

    public String getNicknameData() {
        return nicknameData;
    }


    public NotUniqueNicknameException(String message, String nicknameData) {
        super(message);
        this.nicknameData = nicknameData;
    }
}
