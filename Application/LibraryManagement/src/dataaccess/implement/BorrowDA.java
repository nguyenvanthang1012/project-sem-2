/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.implement;

import identities.Borrow;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A
 */
public class BorrowDA implements dataaccess.IBorrowDA{
    
    Connection con;
    
    public BorrowDA(){
        con = ConnectionDB.getConnection();
    }
    @Override
    public List<Borrow> getAll() {
        List<Borrow> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetAllBorrow}");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new Borrow(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Borrow get(int borrowId) {
        Borrow b = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetBorrow(?) }");
            call.setInt(1, borrowId);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                b = new Borrow(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4)); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    @Override
    public String create(Borrow b) {
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_createBorrow(?,?,?)}");
            call.setInt(1, b.getStudentId());
            call.setInt(2, b.getImployeeId());
            call.registerOutParameter(3, java.sql.Types.NCHAR);
            call.executeUpdate();
            error = call.getString(8);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String update(Borrow b) {
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_UpdateBorrow(?,?,?,?)}");
            call.setInt(1, b.getBorrowId());
            call.setInt(2, b.getStudentId());
            call.setInt(3, b.getImployeeId());
            call.registerOutParameter(4, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(4);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String delete(int borrowId) {
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_DeleteBorrow(?,?)}");
            call.setInt(1, borrowId);
            call.registerOutParameter(2, java.sql.Types.NCHAR);
            call.executeUpdate();
            error = call.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }
    
}
