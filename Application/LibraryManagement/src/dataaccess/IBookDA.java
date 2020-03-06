/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import identities.Book;
import java.util.List;

/**
 *
 * @author A
 */
public interface IBookDA {
    public List<Book> getAll();
    public Book get(int bookId);
    public String create(Book b);
    public String update(Book b);
    public String delete(int bookId);
}
