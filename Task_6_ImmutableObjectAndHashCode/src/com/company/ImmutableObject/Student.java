package com.company.ImmutableObject;

/**
 * Created by Iryna Furmaniuk on 11.07.2021.
 */
public final class Student {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Course course;

    public Student(int id, String firstName, String lastName, String email, Course course) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Course cloneCourse = new Course();
        cloneCourse.setName(course.getName());
        cloneCourse.setDuration(course.getDuration());
        cloneCourse.setPrice(course.getPrice());
        this.course=cloneCourse;
        if (this.id<0){
        throw new IllegalArgumentException("id must be greater than or equal to zero");}

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Course getCourse() {
        Course cloneCourse = new Course();
        cloneCourse.setName(this.course.getName());
        cloneCourse.setPrice(this.course.getPrice());
        cloneCourse.setDuration(this.course.getDuration());
        return cloneCourse;
    }


    public Student setId(int id) {
return new Student(id,this.firstName,this.lastName,this.email, this.course);
    }

    public Student setFirstName(String firstName) {
        return new Student(this.id,firstName,this.lastName,this.email, this.course);
    }

    public Student setLastName(String lastName) {
        return new Student(this.id,this.firstName,lastName,this.email, this.course);
    }

    public Student setEmail(String email) {
        return new Student(this.id,this.firstName,this.lastName,email, this.course);
    }

    public Student setCourse(Course course) {
        return new Student(this.id,this.firstName,this.lastName,this.email, course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", course=" + course +
                '}';
    }
}
