package com.company.HashCode;

import java.util.Objects;

/**
 * Created by Iryna Furmaniuk on 11.07.2021.
 */
public class Student {
    private String name;
    private int age;
    private String group;

    public Student(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    public int hashCode() {
        return (int) (((7 * age + ((name == null) ? 0 : name.hashCode()) )* 7 + (group == null ? 0 : group.hashCode())) * 7);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() == obj.getClass()) {
            Student temp = (Student) obj;
            return name.equals(temp.name) && this.age == temp.age && group.equals(temp.group);
        } else return false;
    }
}
