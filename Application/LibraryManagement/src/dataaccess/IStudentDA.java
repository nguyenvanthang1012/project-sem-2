/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import identities.Student;
import java.util.List;

/**
 *
 * @author A
 */
public interface IStudentDA {
    public List<Student> getAll();
    public Student get(int id);
    public Student getStudentByStudentId(int studentId);
    public boolean create(Student s);
    public boolean update(Student s);
    public boolean delete(int id);
}
