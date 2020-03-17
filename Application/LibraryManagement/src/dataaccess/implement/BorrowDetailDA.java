/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.implement;

import identities.BorrowDetail;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A
 */
public class BorrowDetailDA implements dataaccess.IBorrowDetailDA{
    Connection con;
    
    public BorrowDetailDA(){
        con = ConnectionDB.getConnection();
    }
    @Override
    public List<BorrowDetail> getAll() {
        List<BorrowDetail> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call create proc sp_GetAllBorrowDetail}");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new BorrowDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDate(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public BorrowDetail get(int borrowDetailId) {
        BorrowDetail b = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetBorrowDetail(?)}");
            call.setInt(1, borrowDetailId);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                b = new BorrowDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDate(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    @Override
    public String create(BorrowDetail b) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_CreateBorrowDetail(?,?,?,?)}");
            call.setInt(1, b.getBorrowDetailId());
            call.setInt(2,b.getBookId());
            call.setDate(3, b.getDateAppointment());
            call.registerOutParameter(4, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(4);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String update(BorrowDetail b) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_UpdateBorrowDetail(?,?,?,?,?)}");
            call.setInt(1, b.getBorrowDetailId());
            call.setInt(2, b.getBookId());
            call.setDate(3, b.getDateAppointment());
            call.setDate(4, b.getDateReturn());
            call.registerOutParameter(5, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(5);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String delete(int borrowDetailId) {
        String error =  "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_DeleteBorrowDetail(?,?)}");
            call.setInt(1, borrowDetailId);
            call.registerOutParameter(2, java.sql.Types.NCHAR);
            call.executeUpdate();
            error = call.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDetailDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }
    
}
