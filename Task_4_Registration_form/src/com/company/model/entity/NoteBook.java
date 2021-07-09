package com.company.model.entity;

import com.company.controller.InputNoteToNotebook;

public class NoteBook {
    private String surname;
    private String nickname;

    public NoteBook() {
    }

    public NoteBook(String surname, String nickname) throws NotUniqueNicknameException {
        this.surname = surname;
        this.nickname = setCorrectNickname(nickname);
    }

    public String setCorrectNickname(String nickname) throws NotUniqueNicknameException {
        while (DBNoteBook.checkNickname(nickname)) {
            throw new NotUniqueNicknameException("Not unique nickname ", nickname);
        }
        return nickname;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
