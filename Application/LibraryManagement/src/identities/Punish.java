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
public class Punish {
    private int id;
    private int studentId;
    private float money;
    private String reason;
    private int employeeId;
    private int borrowDetailId;

    public Punish() {
    }

    public Punish(int studentId, float money, String reason, int employeeId, int borrowDetailId) {
        this.studentId = studentId;
        this.money = money;
        this.reason = reason;
        this.employeeId = employeeId;
        this.borrowDetailId = borrowDetailId;
    }

    public Punish(int id, int studentId, float money, String reason, int employeeId, int borrowDetailId) {
        this.id = id;
        this.studentId = studentId;
        this.money = money;
        this.reason = reason;
        this.employeeId = employeeId;
        this.borrowDetailId = borrowDetailId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getBorrowDetailId() {
        return borrowDetailId;
    }

    public void setBorrowDetailId(int borrowDetailId) {
        this.borrowDetailId = borrowDetailId;
    }

    @Override
    public String toString() {
        return "Punish{" + "id=" + id + ", studentId=" + studentId + ", money=" + money + ", reason=" + reason + ", employeeId=" + employeeId + ", borrowDetailId=" + borrowDetailId + '}';
    }
    
    
}
