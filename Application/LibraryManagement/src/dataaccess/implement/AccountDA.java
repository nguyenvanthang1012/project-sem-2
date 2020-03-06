/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.implement;

import identities.Account;
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
public class AccountDA implements dataaccess.IAccountDA{
    
    Connection con;
    
    public AccountDA(){
        con = ConnectionDB.getConnection();
    }
    
    @Override
    public List<Account> getAll() {
        
        List<Account> list = new ArrayList<>();
        
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetAllAccount}");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                Account c = new Account(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Account> search(String name) {
        List<Account> list = new ArrayList<>();
        
        try {
            CallableStatement call = con.prepareCall("{ call sp_SearchAccount(?) }");
            call.setString(1,name);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                Account c = new Account(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Account login(String username, String password) {
        Account c = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_Login(?,?)} ");
            call.setString(1,username);
            call.setString(2,password);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                c = new Account(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public String update(Account a) {
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_UpdateAccount(?,?,?)} ");
            call.setInt(1,a.getAccount_id());
            call.setString(2,a.getPassword());
            call.registerOutParameter(3, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error= call.getString(3);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String create(Account a) {
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_CreateAccount(?,?,?,?) }");
            call.setString(1,a.getUserName());
            call.setString(2,a.getPassword());
            call.setInt(3,a.getDecentralization());
            call.registerOutParameter(4, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(4);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String delete(int idAccount) {
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_DeleteAccount(?,?) }");
            call.setInt(1,idAccount);
            call.registerOutParameter(2, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }
    
}
