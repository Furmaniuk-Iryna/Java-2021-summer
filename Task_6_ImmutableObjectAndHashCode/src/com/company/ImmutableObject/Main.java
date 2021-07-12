package com.company.ImmutableObject;

public class Main {
    public static void main(String[] args) {
        Course course = new Course("java", 23.0, "2 weeks");
        Student student = new Student(123, "Ira", "Furmaniuk", "furmaniuk_ira@gmail.com", course);
        System.out.println(student);

        student.getCourse().setPrice(30.0);
        System.out.println(student);

        student.setId(13);
        System.out.println(student);
    }
}
