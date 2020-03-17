/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import identities.Employee;
import java.util.List;

/**
 *
 * @author A
 */
public interface IEmployeeDA {
    public List<Employee> getAll();
    public Employee get(int employeeId);
    public List<Employee> findByPhone(String phone);
    public List<Employee> findByEmail(String email);
    public Employee getByAccount(int accountId);
    public String create(Employee e);
    public String update(Employee e);
    public String delete(int bookId);
}
