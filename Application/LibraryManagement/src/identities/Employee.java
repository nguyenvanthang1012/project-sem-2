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
    private int employeeId;
    private int accountId;
    private String name;
    private String phone;
    private String email;
    private String home_town;

    public Employee() {
    }

    public Employee(int employeeId, int accountId, String name, String phone, String email, String home_town) {
        this.employeeId = employeeId;
        this.accountId = accountId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.home_town = home_town;
    }

    public Employee(int accountId, String name, String phone, String email, String home_town) {
        this.accountId = accountId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.home_town = home_town;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
        return "Employee{" + "id=" + employeeId + ", accountId=" + accountId + ", name=" + name + ", phone=" + phone + ", email=" + email + ", home_town=" + home_town + '}';
    }
}
