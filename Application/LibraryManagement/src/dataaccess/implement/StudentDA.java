/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.implement;

import identities.Student;
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
public class StudentDA implements dataaccess.IStudentDA{
    Connection con;
    
    public StudentDA(){
        con = ConnectionDB.getConnection();
    }
    @Override
    public List<Student> getAll() {
        List<Student> list= new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetAllStudent}");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new Student(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getByte(4), rs.getString(5), rs.getString(6), rs.getDate(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Student get(int id) {
        Student s = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetStudent(?)}");
            call.setInt(1,id);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                s = new Student(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getByte(4), rs.getString(5), rs.getString(6), rs.getDate(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    @Override
    public List<Student> getStudentByStudentId(String studentId) {
        List<Student> list= new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetStudentByStudentId(?)}");
            call.setString(1, studentId);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new Student(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getByte(4), rs.getString(5), rs.getString(6), rs.getDate(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public String create(Student s) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_CreateStudent(?,?,?,?,?,?,?)}");
            call.setString(1,s.getStudentId());
            call.setString(2,s.getName());
            call.setByte(3, s.getGender());
            call.setString(4,s.getClassName());
            call.setString(5, s.getPhone());
            call.setDate(6,s.getDateofbirth());
            call.registerOutParameter(7, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(7);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String update(Student s) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_UpdateStudent(?,?,?,?,?,?,?,?)}");
            call.setInt(1, s.getId());
            call.setString(2,s.getStudentId());
            call.setString(3,s.getName());
            call.setByte(4, s.getGender());
            call.setString(5,s.getClassName());
            call.setString(6, s.getPhone());
            call.setDate(7,s.getDateofbirth());
            call.registerOutParameter(8, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(8);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String delete(int id) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_DeleteStudent(?,?)");
            call.setInt(1, id);
            call.registerOutParameter(2, java.sql.Types.NCHAR);
            call.executeUpdate();
            error = call.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }
    
}
