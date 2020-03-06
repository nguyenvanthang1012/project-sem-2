/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import identities.Category;
import java.util.List;

/**
 *
 * @author A
 */
public interface ICategoryDA {
    public List<Category> getAll();
    public Category search(int catId);
    public List<Category> searchByName(String name);
    public boolean create(Category c);
    public boolean update(Category c);
    public boolean delete(int catId);
}
