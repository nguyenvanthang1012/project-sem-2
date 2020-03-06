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
    private int borrowId;
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

    public Borrow(int borrowId, int studentId, int imployeeId, Date dateBorrow) {
        this.borrowId = borrowId;
        this.studentId = studentId;
        this.imployeeId = imployeeId;
        this.dateBorrow = dateBorrow;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
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
        return "Borrow{" + "id=" + borrowId + ", studentId=" + studentId + ", imployeeId=" + imployeeId + ", dateBorrow=" + dateBorrow + '}';
    }
    
    
}
