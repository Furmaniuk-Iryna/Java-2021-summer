package com.company.model.entity;

/**
 * Created by Iryna Furmaniuk on 08.07.2021.
 */
public class NoteBook {
    private String surname;
    private String nickname;

    public NoteBook(String surname, String nickname) throws NotUniqueNicknameException {
        if (DBNoteBook.checkNickname(nickname)) {
            throw new NotUniqueNicknameException("Not unique nickname ", nickname);
        }
        this.surname = surname;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
