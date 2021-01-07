/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author The Godfather
 */
public class Student implements Serializable{
    
    private int studentId;
    private String name, birth, course, address;

    public Student() {
    }

    public Student(int studentId, String name, String birth, String course, String address) {
        this.studentId = studentId;
        this.name = name;
        this.birth = birth;
        this.course = course;
        this.address = address;
    }

    public Student(String name, String birth, String course, String address) {
        this.name = name;
        this.birth = birth;
        this.course = course;
        this.address = address;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", name=" + name + ", birth=" + birth + ", course=" + course + ", address=" + address + '}';
    }
    
    public Object[] toObject(){
        return new Object[] {studentId, name, birth, course, address};
    }
    
}
