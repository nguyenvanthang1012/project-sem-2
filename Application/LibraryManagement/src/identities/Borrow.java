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
public class Borrow {
    private int id;
    private int studentId;
    private int imployeeId;
    private Date dateBorrow;

    public Borrow() {
    }

    public Borrow(int studentId, int imployeeId, Date dateBorrow) {
        this.studentId = studentId;
        this.imployeeId = imployeeId;
        this.dateBorrow = dateBorrow;
    }

    public Borrow(int id, int studentId, int imployeeId, Date dateBorrow) {
        this.id = id;
        this.studentId = studentId;
        this.imployeeId = imployeeId;
        this.dateBorrow = dateBorrow;
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

    public int getImployeeId() {
        return imployeeId;
    }

    public void setImployeeId(int imployeeId) {
        this.imployeeId = imployeeId;
    }

    public Date getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(Date dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    @Override
    public String toString() {
        return "Borrow{" + "id=" + id + ", studentId=" + studentId + ", imployeeId=" + imployeeId + ", dateBorrow=" + dateBorrow + '}';
    }
    
    
}
