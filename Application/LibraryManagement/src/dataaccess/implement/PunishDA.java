/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.implement;

import identities.Punish;
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
public class PunishDA implements dataaccess.IPunishDA{
    
    Connection con;

    public PunishDA(){
        con = ConnectionDB.getConnection();
    }
    @Override
    public List<Punish> getAll() {
        List<Punish> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetAllPunish}");
            ResultSet rs = call.executeQuery();
            if(rs.next()){
                list.add(new Punish(rs.getInt(1), rs.getInt(2), rs.getFloat(3),rs.getString(4) , rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PunishDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Punish get(int punishId) {
        Punish p = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetPunish(?)}");
            call.setInt(1,punishId);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                p = new Punish(rs.getInt(1), rs.getInt(2), rs.getFloat(3),rs.getString(4) , rs.getInt(5), rs.getInt(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PunishDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public String create(Punish p) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_CreatePunish(?,?,?,?,?,?)}");
            call.setInt(1,p.getStudentId());
            call.setFloat(2, p.getMoney());
            call.setString(3,p.getReason());
            call.setInt(4,p.getEmployeeId());
            call.setInt(5,p.getBorrowDetailId());
            call.registerOutParameter(6, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(6);
        } catch (SQLException ex) {
            Logger.getLogger(PunishDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String update(Punish p) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_CreatePunish(?,?,?,?,?,?,?)}");
            call.setInt(1, p.getPunishId());
            call.setInt(2,p.getPunishId());
            call.setFloat(3, p.getMoney());
            call.setString(4,p.getReason());
            call.setInt(5,p.getEmployeeId());
            call.setInt(6,p.getBorrowDetailId());
            call.registerOutParameter(7, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(7);
        } catch (SQLException ex) {
            Logger.getLogger(PunishDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String delete(int punishId) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_DeletePunish(?,?)}");
            call.setInt(1, punishId);
            call.registerOutParameter(2, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(PunishDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }
    
}
