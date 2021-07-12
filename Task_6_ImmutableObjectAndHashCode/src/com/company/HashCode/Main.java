package com.company.HashCode;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("ira", 19, "in-201");
        Student student1 = new Student("ira", 19, "in-201");
        Student student2 = student;

        System.out.println(student.hashCode());
        System.out.println(student1.hashCode());
        System.out.println(student2.hashCode());

        System.out.println(student.equals(student1));
        System.out.println(student.equals(null));
    }
}
