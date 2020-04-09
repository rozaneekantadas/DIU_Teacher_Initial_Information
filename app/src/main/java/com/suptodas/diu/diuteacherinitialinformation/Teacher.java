package com.suptodas.diu.diuteacherinitialinformation;

public class Teacher {

    private String initial, name, department, designation, email, phone;

    public Teacher(){

    }

    public Teacher(String initial, String name, String designation, String department, String phone,  String email) {
        this.initial = initial;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.email = email;
        this.phone = phone;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
