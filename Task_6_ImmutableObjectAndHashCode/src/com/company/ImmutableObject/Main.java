package com.company.ImmutableObject;

public class Main {
    public static void main(String[] args) {
        Course course = new Course("java",1000.0,"6 months",new  Instructor("Olena","Yaremchuk"));
        System.out.println(course);

        course.setDuration("2 months");
        System.out.println(course);

        course.setInstructor(new Instructor("Iryna","Korol"));
        System.out.println(course);
    }
}
