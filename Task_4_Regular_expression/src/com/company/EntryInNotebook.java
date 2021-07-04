package com.company;

import java.util.ArrayList;

public class EntryInNotebook {
    private String lastName;
    private String nickname;
    private ArrayList<EntryInNotebook> entriesInTheNotebook = new ArrayList<>();

    public EntryInNotebook() {
    }

    public EntryInNotebook(String lastName, String nickname) {
        this.lastName = lastName;
        this.nickname = nickname;
    }

    public void writeInNotebook() {
        entriesInTheNotebook.add(new EntryInNotebook(getLastName(), getNickname()));
    }

    public ArrayList<EntryInNotebook> getEntriesInTheNotebook() {
        return entriesInTheNotebook;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
