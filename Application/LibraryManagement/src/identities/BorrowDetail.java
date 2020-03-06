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
public class BorrowDetail {
    private int borrowDetailId;
    private int borrowId;
    private int bookId;
    private Date dateAppointment;
    private Date dateReturn;

    public BorrowDetail() {
    }

    public BorrowDetail(int borrowId, int bookId, Date dateAppointment, Date dateReturn) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.dateAppointment = dateAppointment;
        this.dateReturn = dateReturn;
    }

    public BorrowDetail(int borrowDetailId, int borrowId, int bookId, Date dateAppointment, Date dateReturn) {
        this.borrowDetailId = borrowDetailId;
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.dateAppointment = dateAppointment;
        this.dateReturn = dateReturn;
    }

    public int getBorrowDetailId() {
        return borrowDetailId;
    }

    public void setBorrowDetailId(int borrowDetailId) {
        this.borrowDetailId = borrowDetailId;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Date dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }
    
    
    @Override
    public String toString() {
        return "BorrowDetail{" + "id=" + borrowDetailId + ", borrowId=" + borrowId + ", bookId=" + bookId + ", dateAppointment=" + dateAppointment + ", dateReturn=" + dateReturn + '}';
    }
}
