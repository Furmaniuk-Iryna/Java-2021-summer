package com.company.model.entity;

/**
 * Created by Iryna Furmaniuk on 08.07.2021.
 */
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
