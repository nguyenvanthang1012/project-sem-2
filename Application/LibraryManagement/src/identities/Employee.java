/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identities;

/**
 *
 * @author A
 */
public class Employee {
    private int id;
    private int idAccout;
    private String name;
    private String phone;
    private String email;
    private String home_town;

    public Employee() {
    }

    public Employee(int id, int idAccout, String name, String phone, String email, String home_town) {
        this.id = id;
        this.idAccout = idAccout;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.home_town = home_town;
    }

    public Employee(int idAccout, String name, String phone, String email, String home_town) {
        this.idAccout = idAccout;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.home_town = home_town;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccout() {
        return idAccout;
    }

    public void setId_accout(int idAccout) {
        this.idAccout = idAccout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome_town() {
        return home_town;
    }

    public void setHome_town(String home_town) {
        this.home_town = home_town;
    }
    
    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", id_accout=" + idAccout + ", name=" + name + ", phone=" + phone + ", email=" + email + ", home_town=" + home_town + '}';
    }
}
