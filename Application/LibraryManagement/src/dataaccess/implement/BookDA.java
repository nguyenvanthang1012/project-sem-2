/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.implement;

import identities.Book;
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
public class BookDA implements dataaccess.IBookDA{
    
    Connection con;
    
    public BookDA(){
        con = ConnectionDB.getConnection();
    }
    @Override
    public List<Book> getAll() {
        List<Book> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetAllBook }");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getInt(8),rs.getFloat(9)));  
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Book get(int bookId) {
        Book b = null;
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetBook(?,?) }");
            call.setInt( 1, bookId);
            call.registerOutParameter(2, java.sql.Types.NVARCHAR);
            ResultSet rs = call.executeQuery();
            error = call.getString(2);
            while(rs.next()){
                b = new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7),rs.getInt(8),rs.getFloat(9));  
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    @Override
    public String create(Book b) {
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_CreateBook(?,?,?,?,?,?,?,?)}");
            call.setString(1, b.getName());
            call.setInt(2,b.getCatId());
            call.setString(3,b.getAuthor());
            call.setString(4,b.getDescription());
            call.setDate(5,b.getPublacationDate());
            call.setInt(6,b.getQuantity());
            call.setFloat(7,b.getPrice());
            call.registerOutParameter(8, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(8);
        } catch (SQLException ex) {
            Logger.getLogger(BookDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String update(Book b) {
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_UpdateBook(?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, b.getBookId());
            call.setString(2, b.getName());
            call.setInt(3,b.getCatId());
            call.setString(4,b.getAuthor());
            call.setString(5,b.getDescription());
            call.setDate(6,b.getPublacationDate());
            call.setInt(7,b.getQuantity());
            call.setFloat(8,b.getPrice());
            call.registerOutParameter(9, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(9);
        } catch (SQLException ex) {
            Logger.getLogger(BookDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String delete(int bookId) {
        String error = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_DeleteBook(?,?) }");
            call.setInt(1, bookId);
            call.registerOutParameter(2, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(BookDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }
    
}
