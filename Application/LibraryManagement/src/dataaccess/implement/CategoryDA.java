/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.implement;

import identities.Category;
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
public class CategoryDA implements dataaccess.ICategoryDA{
    Connection con;
    
    public CategoryDA(){
        con = ConnectionDB.getConnection();
    }
    @Override
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetAllCategory}");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Category get(int catId) {
        Category c = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetAllCategory}");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                c = new Category(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public List<Category> searchByName(String name) {
        List<Category> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetAllCategory(?)}");
            call.setString(1, name);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public String create(Category c) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_CreateCategory(?,?)}");
            call.setString(1,c.getName());
            call.registerOutParameter(2, java.sql.Types.NCHAR);
            call.executeUpdate();
            error = call.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String update(Category c) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_UpdateCategory(?,?,?)}");
            call.setInt(1, c.getCatId());
            call.setString(2,c.getName());
            call.registerOutParameter(3, java.sql.Types.NCHAR);
            call.executeUpdate();
            error = call.getString(3);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String delete(int catId) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_UpdateCategory(?,?)}");
            call.setInt(1, catId);
            call.registerOutParameter(2, java.sql.Types.NCHAR);
            call.executeUpdate();
            error = call.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }
    
}
