/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identities;

import java.sql.Date;

/**
 *
 * @author A
 */
public class Student {
    private int id;
    private String studentId;
    private String name;
    private String className;
    private String phone;
    private Date dateofbirth;

    public Student() {
    }

    public Student(String studentId, String name, String className, String phone, Date dateofbirth) {
        this.studentId = studentId;
        this.name = name;
        this.className = className;
        this.phone = phone;
        this.dateofbirth = dateofbirth;
    }

    public Student(int id, String studentId, String name, String className, String phone, Date dateofbirth) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.className = className;
        this.phone = phone;
        this.dateofbirth = dateofbirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", studentId=" + studentId + ", name=" + name + ", className=" + className + ", phone=" + phone + ", dateofbirth=" + dateofbirth + '}';
    }

    
}
