/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.implement;

import identities.Employee;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author A
 */
public class EmployeeDA implements dataaccess.IEmployeeDA{
    
    Connection con;
    
    public EmployeeDA(){
        con = ConnectionDB.getConnection();
    }
    
    @Override
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetAllEmployee}");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new Employee(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Employee get(int employeeId) {
        Employee e = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetEmployee(?)}");
            call.setInt(1,employeeId);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                e = new Employee(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

    @Override
    public List<Employee> findByPhone(String phone) {
        List<Employee> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_FindByPhoneEmployee(?)}");
            call.setString(1,phone);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new Employee(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Employee> findByEmail(String email) {
        List<Employee> list = new ArrayList<>();
        try {
            CallableStatement call = con.prepareCall("{ call sp_FindByEmailEmployee(?)}");
            call.setString(1,email);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                list.add(new Employee(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Employee getByAccount(int accountId) {
        Employee e = null;
        try {
            CallableStatement call = con.prepareCall("{ call sp_GetByAccountEmployee(?,?)}");
            call.setInt(1, accountId);
            call.registerOutParameter(2, java.sql.Types.NVARCHAR);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                e = new Employee(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    
    @Override
    public String create(Employee e) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_CreateEmployee(?,?,?,?,?,?,?)}");
            call.setInt(1,e.getAccountId());
            call.setString(2,e.getName());
            call.setDate(3,e.getDateofbirth());
            call.setString(4,e.getPhone());
            call.setString(5, e.getEmail());
            call.setString(6,e.getHome_town());
            call.registerOutParameter(7, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(7);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }
    
    @Override
    public String update(Employee e) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_UpdateEmployee(?,?,?,?,?,?,?,?)}");
            call.setInt(1,e.getEmployeeId());
            call.setInt(2,e.getAccountId());
            call.setString(3,e.getName());
            call.setDate(4,e.getDateofbirth());
            call.setString(5,e.getPhone());
            call.setString(6, e.getEmail());
            call.setString(7,e.getHome_town());
            call.registerOutParameter(8, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(8);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    @Override
    public String delete(int bookId) {
        String error = "";
        try {
            CallableStatement call = con.prepareCall("{ call sp_DeleteEmployee(?,?)}");
            call.setInt(1, bookId);
            call.registerOutParameter(2, java.sql.Types.NVARCHAR);
            call.executeUpdate();
            error = call.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }
    
}
