package com.company.ImmutableObject;

/**
 * Created by Iryna Furmaniuk on 11.07.2021.
 */
public final class Course {
    private final String name;
    private final double price;
    private final String duration;
    private final Instructor instructor;

    public Course(String name, double price, String duration, Instructor instructor) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        Instructor cloneInstructor = new Instructor();
        cloneInstructor.setFirstName(instructor.getFirstName());
        cloneInstructor.setLastName(instructor.getLastName());
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    public Course setName(String name) {
        return new Course(name, this.price, this.duration, this.instructor);
    }

    public double getPrice() {
        return price;
    }

    public Course setPrice(double price) {
        return new Course(this.name, price, this.duration, this.instructor);
    }

    public String getDuration() {
        return duration;
    }

    public Course setDuration(String duration) {

        return new Course(this.name, this.price, duration, this.instructor);
    }

    public Instructor getInstructor() {
        Instructor cloneInstructor = new Instructor();
        cloneInstructor.setFirstName(instructor.getFirstName());
        cloneInstructor.setLastName(instructor.getLastName());
        return cloneInstructor;
    }

    public Course setInstructor(Instructor instructor) {
        return new Course(this.name, this.price, this.duration, instructor);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", duration='" + duration + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
