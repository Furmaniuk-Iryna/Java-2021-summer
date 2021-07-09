package com.company.model.entity;

public enum DBNoteBook {
    FIRST_NOTE("Furmaniuk", "__--ira--__"),
    SECOND_NOTE("Yaremchuk", "viktoria_12986");

    private String surname;
    private String nickname;

    DBNoteBook(String surname, String nickname) {
        this.surname = surname;
        this.nickname = nickname;
    }

    public static boolean checkNickname(String nickname) {
        for (DBNoteBook note : DBNoteBook.values()) {
            if (note.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public String getNickname() {
        return nickname;
    }

}
